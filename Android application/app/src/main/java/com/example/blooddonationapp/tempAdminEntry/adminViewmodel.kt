package com.example.blooddonationapp.tempAdminEntry

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.home.data.TimestampToLocalDate
import com.example.blooddonationapp.home.data.globalNotificationList
import com.example.blooddonationapp.home.data.notification
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.util.Date

class adminViewmodel:ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()


    @RequiresApi(Build.VERSION_CODES.O)
    fun newNotification(){
//        val instant = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()
//        val theDate = Date.from(instant)
//        val timestamp = Timestamp(theDate)
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
                        errorMessage="Just kidding, notification pushed successfully"
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
//        val instant = newAnnouncement.date.atStartOfDay(ZoneId.systemDefault()).toInstant()
//        val theDate = Date.from(instant)
//        val timestamp = Timestamp(theDate)

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