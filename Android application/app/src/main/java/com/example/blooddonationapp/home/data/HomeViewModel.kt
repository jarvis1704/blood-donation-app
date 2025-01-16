package com.example.blooddonationapp.home.data

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.blooddonationapp.global.data.errorMessage
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
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

    @SuppressLint("NewApi")
    suspend fun FetchNotifications() {
        var lastNotificationSeen: Timestamp? by mutableStateOf(null)
        var lastSeenLocalDateTime: LocalDateTime? by mutableStateOf(null)
        newNotificationsCounter = 0
        try {
            //get the last seen timestamp
            val doc = _db.collection("userdetails").document(_auth.currentUser!!.uid)
                .get().await()
            if (doc != null) {
                lastNotificationSeen = doc.getTimestamp("lastNotificationSeen")
                lastSeenLocalDateTime = lastNotificationSeen?.let { TimestampToLocalDateTime(it) }
            }

            //get all notifications
            val list = _db.collection("notifications").get().await()
            if (list != null) {
                val notificationList = list.documents.mapNotNull { doc ->
                    val data = doc.data
                    data?.let {
                        notification(
                            body = it["body"].toString(),
                            type = "",
                            title = "",
                            bloodtype = "",
                            location = it["location"].toString(),
                            dateAndTime = (it["date&time"] as? Timestamp)?.let { timestamp ->
                                TimestampToLocalDateTime(timestamp)
                            },
                        )
                    }
                }.sortedByDescending { it.dateAndTime }
                globalNotificationList = notificationList

                //now compare to identify new notifications
                notificationList.forEach { notification ->
                    notification.dateAndTime?.let { notificationDate ->
                        if (lastSeenLocalDateTime == null || notificationDate.isAfter(
                                lastSeenLocalDateTime
                            )
                        ) {
                            newNotificationsCounter++
                        }
                    }
                }
            }
        } catch (e: Exception) {
            errorMessage = e.message.toString()
        }
    }

    suspend fun FetchBloodRequests() {
        try {
            //get all blood req
            val list = _db.collection("blood_requests").get().await()
            if (list != null) {
                val bloodReqList = list.documents.mapNotNull { doc ->
                    val data = doc.data
                    data?.let {
                        bloodRequest(
                            bloodtype = it["bloodtype"].toString(),
                            hospital = it["hospital"].toString(),
                            date = (it["date"] as? Timestamp)?.let { timestamp ->
                                TimestampToLocalDateTime(timestamp)
                            }
                        )
                    }
                }.sortedByDescending { it.date }
                globalBloodRequestList = bloodReqList
            }
        } catch (e: Exception) {
            errorMessage = e.message.toString()
        }
    }

    suspend fun FetchAnnouncements() {
        try {
            val list = _db.collection("announcements").get().await()
            if (list != null) {
                val announcementList = list.documents.mapNotNull { doc ->
                    val data = doc.data
                    data?.let {
                        announcement(
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
}