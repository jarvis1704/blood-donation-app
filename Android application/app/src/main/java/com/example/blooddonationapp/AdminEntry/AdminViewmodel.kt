package com.example.blooddonationapp.AdminEntry

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.global.data.infoMessage
import com.example.blooddonationapp.home.data.TimestampToLocalDateTime
import com.example.blooddonationapp.home.data.announcement
import com.example.blooddonationapp.home.data.bloodRequest
import com.example.blooddonationapp.home.data.globalAnnouncementList
import com.example.blooddonationapp.home.data.globalBloodRequestList
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class AdminViewmodel @Inject constructor():ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()


    fun newBloodReq(goto_activeBloodreqs:()-> Unit = {}, onsuccess:()-> Unit={}){
        try {
            val datamap = mapOf(
                "patient" to newBloodRequest.patientname,
                "patientage" to newBloodRequest.patientage,
                "patientgender" to newBloodRequest.patientgender,
                "attendant" to newBloodRequest.attendantname,
                "attendantphoneno" to newBloodRequest.attendantphoneno,
                "bloodtype" to newBloodRequest.bloodgroup,
                "hospital" to newBloodRequest.hospital,
                "urgencylevel" to newBloodRequest.urgencylevel,
                "unitsrequired" to newBloodRequest.unitsrequired,
                "details" to newBloodRequest.details,
                "date" to Timestamp.now()
            )
            db.collection("blood_requests").document()
                .set(datamap, SetOptions.merge())
                .addOnSuccessListener {
                    onsuccess()
                    ClearNewBloodReqObj()
                    goto_activeBloodreqs()
                }
                .addOnFailureListener {
                    errorMessage = it.message.toString()
                }
        }catch (e:Exception){
            errorMessage = e.message.toString()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun newAnnouncement(goto_activeAnnouncements:()-> Unit){
        val localDateTime = newAnnouncement.date.atTime(newAnnouncement.time)
        val instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant()
        val timestamp = Timestamp(instant.epochSecond, instant.nano)

        try {
            val datamap = mapOf(
                "title" to newAnnouncement.title,
                "location" to newAnnouncement.location,
                "date&time" to timestamp
            )
            if (true){
                db.collection("announcements").document()
                    .set(datamap, SetOptions.merge())
                    .addOnSuccessListener {
                        ClearNewAnnouncementObj()
                        goto_activeAnnouncements()
                    }
                    .addOnFailureListener {
                        errorMessage = it.message.toString()
                    }
            }
        }catch (e:Exception){
            errorMessage = e.message.toString()
        }
    }

    fun getPendingAadhar(){
        viewModelScope.launch {
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

    fun DeleteBloodRequest(req:bloodRequest){
        viewModelScope.launch {
            try {
                db.collection("blood_requests").document(req.id)
                    .delete()
                    .addOnSuccessListener {
                        globalBloodRequestList = globalBloodRequestList?.minus(req)
                    }
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }

    fun DeleteAnnouncement(announcement: announcement){
        viewModelScope.launch {
            try {
                db.collection("announcements").document(announcement.id)
                    .delete()
                    .addOnSuccessListener {
                        globalAnnouncementList = globalAnnouncementList?.minus(announcement)
                    }
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }

    fun GetActivePasskeys(){
        ActivePasskeysList = emptyList()
        viewModelScope.launch {
            try {
                val document = db.collection("passkey").get().await()
                val list = document.documents.mapNotNull { doc->
                    val temp = doc.id
                    val data = doc.data
                    data?.let {
                        Passkey(
                            id = temp,
                            key = it["passkey"].toString()
                        )
                    }
                }
                ActivePasskeysList = list
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }finally {
                isFetchingPasskeys = false
            }
        }
    }

    fun DeletePasskey(key: Passkey){
        if (ActivePasskeysList.size > 1){
            viewModelScope.launch {
                try {
                    db.collection("passkey").document(key.id)
                        .delete()
                        .addOnSuccessListener {
                            ActivePasskeysList = ActivePasskeysList.minus(key)
                        }
                }catch (e:Exception){
                    errorMessage = e.message.toString()
                }
            }
        }else{
            errorMessage = "Minimum 1 passkey is required!"
        }
    }

    fun AddPasskey(key: String){
        if (key.isNotEmpty()){
            viewModelScope.launch {
                try {
                    val data = mapOf(
                        "passkey" to key
                    )
                    db.collection("passkey").add(data)
                        .addOnSuccessListener {
                            tempNewPasskey = ""
                            GetActivePasskeys()
                            isNewPasskeyDialogue = false
                        }
                }catch (e: Exception){
                    errorMessage = e.message.toString()
                }
            }
        }else{
            errorMessage = "Key cannot be empty"
        }
    }

    fun getPendingBloodRequests(){
        viewModelScope.launch {
            try {
                val list = db.collection("pending_blood_requests").get().await()
                if (list != null){
                    val List = list.documents.mapNotNull { doc->
                        val data = doc.data
                        val id = doc.id
                        data?.let {
                            bloodRequest(
                                id = id,
                                patientname = it["patient"].toString(),
                                patientage = it["patientage"].toString(),
                                patientgender = it["patientgender"].toString(),
                                attendantname = it["attendant"].toString(),
                                attendantphoneno = it["attendantphoneno"].toString(),
                                bloodtype = it["bloodtype"].toString(),
                                hospital = it["hospital"].toString(),
                                urgencylevel = it["urgencylevel"].toString(),
                                unitsrequired = it["unitsrequired"].toString(),
                                details = it["details"].toString(),
                                date = (it["date"] as? Timestamp)?.let { timestamp ->
                                    TimestampToLocalDateTime(timestamp)
                                }
                            )
                        }
                    }.sortedByDescending { it.date }
                    bloodreqPendingList = List
                }
            }catch (e:Exception){
                errorMessage=e.message.toString()
            }
        }
    }

    fun DeletePendingBloodReq(id: String){
        viewModelScope.launch {
            try {
                db.collection("pending_blood_requests").document(id)
                    .delete()
                    .addOnSuccessListener {
                        infoMessage = "Process Executed Successfully"
                        getPendingBloodRequests()
                    }
            }catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}