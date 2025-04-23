package com.example.blooddonationapp.home.data

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blooddonationapp.AdminEntry.data.ClearNewBloodReqObj
import com.example.blooddonationapp.AdminEntry.data.newBloodRequest
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.global.data.infoMessage
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val _auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _db: FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun getRegistrationEntryByString(
        entry: String,                                 //what type of entry we want to get, username, gender, area etc
    ): String {
        if (_auth.currentUser != null) {
            try {
                val document =
                    _auth.currentUser?.let { _db.collection("userdetails").document(it.uid) }
                        ?.get()?.await()
                if (document != null) {
                    return document.getString(entry) ?: ""
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
        return ""
    }

    suspend fun getAadharDetails(
        entry: String
    ):Any{
        if (_auth.currentUser != null) {
            try {
                val document =
                    _auth.currentUser?.let { _db.collection("aadhardetails").document(it.uid) }
                        ?.get()?.await()
                if (document != null) {
                    return document.getString(entry) ?: ""
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
        return ""
    }

    suspend fun saveBoolean(collection: String, field:String, value: Boolean){
        try {
            val document = _auth.currentUser?.let { _db.collection(collection).document(it.uid) }
                ?.get()?.await()
            if (document != null) {
                _db.collection(collection).document(_auth.currentUser!!.uid)
                    .update(field, true)
                    .addOnSuccessListener {

                    }.addOnFailureListener {
                        errorMessage = it.message.toString()
                    }
            }
        } catch (e: Exception) {
            errorMessage = e.message.toString()
        }
    }

    suspend fun getBoolean(collection: String, field: String):Boolean{
        try {
            val document =
                _auth.currentUser?.let { _db.collection(collection).document(it.uid) }
                    ?.get()?.await()
            if (document != null) {
                return document.getBoolean(field)?:false
            }
        } catch (e: Exception) {
            errorMessage = e.message.toString()
        }
        return false
    }

    fun FetchBloodRequests() {
        viewModelScope.launch {
            try {
                //get all blood req
                val list = _db.collection("blood_requests").get().await()
                if (list != null) {
                    val bloodReqList = list.documents.mapNotNull { doc ->
                        val temp = doc.id
                        val data = doc.data
                        data?.let {
                            bloodRequest(
                                id = temp,
                                patientname = it["patient"].toString(),
                                bloodtype = it["bloodtype"].toString(),
                                hospital = it["hospital"].toString(),
                                date = (it["date"] as? Timestamp)?.let { timestamp ->
                                    TimestampToLocalDateTime(timestamp)
                                },
                                attendantname = it["attendant"].toString(),
                                attendantphoneno = it["attendantphoneno"].toString(),
                                patientage = it["patientage"].toString(),
                                patientgender = it["patientgender"].toString(),
                                urgencylevel = it["urgencylevel"].toString(),
                                unitsrequired = it["unitsrequired"].toString(),
                                details = it["details"].toString(),
                            )
                        }
                    }.sortedByDescending { it.date }
                    globalBloodRequestList = bloodReqList
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun FetchAnnouncements() {
        viewModelScope.launch {
            try {
                val list = _db.collection("announcements").get().await()
                if (list != null) {
                    val announcementList = list.documents.mapNotNull { doc ->
                        val data = doc.data
                        val temp = doc.id
                        data?.let {
                            announcement(
                                id = temp,
                                title = it["title"].toString(),
                                location = it["location"].toString(),
                                dateAndTime = (it["date&time"] as? Timestamp)?.let { timestamp ->
                                    TimestampToLocalDateTime(timestamp)
                                }
                            )
                        }
                    }.sortedBy { it.dateAndTime }
                    globalAnnouncementList = announcementList
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    @SuppressLint("NewApi")
    suspend fun updateNotificationLastSeen() {
        var timestamp: Timestamp = Timestamp.now()
        try {
            val document = _auth.currentUser?.let { _db.collection("userdetails").document(it.uid) }
                ?.get()?.await()
            if (document != null) {
                _db.collection("userdetails").document(_auth.currentUser!!.uid)
                    .update("lastNotificationSeen", timestamp)
                    .addOnSuccessListener {

                    }.addOnFailureListener {
                        errorMessage = it.message.toString()
                    }
            }
        } catch (e: Exception) {
            errorMessage = e.message.toString()
        }
    }

    fun newBloodReq(
        goto_parentpage:()-> Unit
    ){
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
            _db.collection("pending_blood_requests").document()
                .set(datamap, SetOptions.merge())
                .addOnSuccessListener {
                    ClearNewBloodReqObj()
                    infoMessage = "Blood request submitted successfully.\n\nOur admins will review the response and post it globally in a short while."
                    goto_parentpage()
                }
                .addOnFailureListener {
                    errorMessage = it.message.toString()
                }
        }catch (e:Exception){
            errorMessage = e.message.toString()
        }
    }
}