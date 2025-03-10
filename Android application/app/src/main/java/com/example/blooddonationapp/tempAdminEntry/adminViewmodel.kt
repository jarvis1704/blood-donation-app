package com.example.blooddonationapp.tempAdminEntry

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.home.data.notification
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.type.DateTime
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

class adminViewmodel:ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()


    @RequiresApi(Build.VERSION_CODES.O)
    fun newBloodReq(){
        try {
            val datamap = mapOf(
                "bloodtype" to newBloodRequest.bloodgroup,
                "hospital" to newBloodRequest.hospital,
                "details" to newBloodRequest.details,
                "date" to Timestamp.now()
            )
            if (true){
                db.collection("blood_requests").document()
                    .set(datamap, SetOptions.merge())
                    .addOnSuccessListener {
                        errorMessage="Just kidding, blood req pushed successfully"
                    }
                    .addOnFailureListener {
                        errorMessage = it.message.toString()
                    }
            }
        }catch (e:Exception){
            errorMessage = e.message.toString()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun newAnnouncement(){
        val instant2 = newAnnouncement.time.atDate(newAnnouncement.date).atZone(ZoneId.systemDefault()).toInstant()
        val theTime = Date.from(instant2)
        val timestamp2 = Timestamp(theTime)
        try {
            val datamap = mapOf(
                "title" to newAnnouncement.title,
                "location" to newAnnouncement.location,
                "date&time" to timestamp2
            )
            if (true){
                db.collection("announcements").document()
                    .set(datamap, SetOptions.merge())
                    .addOnSuccessListener {
                        errorMessage="Just kidding, announcement pushed successfully"
                    }
                    .addOnFailureListener {
                        errorMessage = it.message.toString()
                    }
            }
        }catch (e:Exception){
            errorMessage = e.message.toString()
        }
    }

//    @SuppressLint("NewApi")
//    fun newNotification(type:String, bloodtype:String="", location:String="", title:String=""){
//        when(type){
//            "bloodrequest"->{
//                //here, bloodtype and location cannot be empty
//                if (bloodtype =="" || location == ""){
//                    errorMessage = "Error: Not enough details to push notification"
//                }
//                var body = "Emergency $bloodtype Blood Needed"
//                try {
//                    val datamap = mapOf(
//                        "body" to body,
//                        "location" to location,
//                        "date&time" to Timestamp.now()
//                    )
//                    db.collection("notifications").document()
//                        .set(datamap, SetOptions.merge())
//                        .addOnSuccessListener {
//                            errorMessage="Just kidding, notification pushed successfully"
//                        }
//                        .addOnFailureListener {
//                            errorMessage = it.message.toString()
//                        }
//                }catch (e:Exception){
//                    errorMessage = e.message.toString()
//                }
//            }
//            "announcement"->{
//                //here, title location and time cannot be empty
//                if (title =="" || location == ""){
//                    errorMessage = "Error: Not enough details to push notification"
//                }
//                var body = "New Event: $title upcoming"
//                try {
//                    val datamap = mapOf(
//                        "body" to body,
//                        "location" to newAnnouncement.location,
//                        "date&time" to Timestamp.now()
//                    )
//                    db.collection("notifications").document()
//                        .set(datamap, SetOptions.merge())
//                        .addOnSuccessListener {
//                            errorMessage="Just kidding, notification pushed successfully"
//                        }
//                        .addOnFailureListener {
//                            errorMessage = it.message.toString()
//                        }
//                }catch (e:Exception){
//                    errorMessage = e.message.toString()
//                }
//            }
//            "aadhar"->{
//
//            }else->{
//                //ignore
//                errorMessage = "Error: Notification type not recognized"
//            }
//        }
//    }

    suspend fun getPendingAadhar(){
        try {
            val aadharlist = db.collection("aadhardetails").get().await()
            if (aadharlist != null){
                val aadharList = aadharlist.documents.mapNotNull { doc->
                    val data = doc.data
                    data?.let {
                        aadharUser(
                            useremail = it["useremail"].toString(),
                            aadharNo = it["aadharNo"].toString().toLongOrNull(),
                            aadharDOB = it["aadharDOB"].toString().toLongOrNull(),
                            aadharStatus = it["aadharStatus"].toString(),
                            aadharPhotoString = it["imageData"].toString()
                        )
                    }
                }.filter { it.aadharStatus=="submitted" }
                aadharPendingList = aadharList
            }
        }catch (e:Exception){
            errorMessage=e.message.toString()
        }
    }
}