package com.example.blooddonationapp.AdminEntry

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blooddonationapp.global.data.errorMessage
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
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class AdminViewmodel @Inject constructor():ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()


    fun newBloodReq(){
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
    }

    fun AddPasskey(key: String){
        viewModelScope.launch {
            try {
                val data = mapOf(
                    "passkey" to key
                )
                db.collection("passkey").add(data)
                    .addOnSuccessListener {
                        GetActivePasskeys()
                        isNewPasskeyDialogue = false
                    }
            }catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}