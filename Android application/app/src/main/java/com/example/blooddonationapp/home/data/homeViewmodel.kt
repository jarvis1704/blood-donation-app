package com.example.blooddonationapp.home.data

import androidx.lifecycle.ViewModel
import com.example.blooddonationapp.global.data.errorMessage
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class homeViewmodel:ViewModel() {
    private val _auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val _db : FirebaseFirestore = FirebaseFirestore.getInstance()

    suspend fun getRegistrationEntryByString(
        entry:String,                                 //what type of entry we want to get, username, gender, area etc
         ):String{
        if (_auth.currentUser != null){
            try {
                val document = _auth.currentUser?.let { _db.collection("userdetails").document(it.uid) }
                    ?.get()?.await()
                if (document != null){
                    return document.getString(entry)?:""
                }
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
        return ""
    }

    suspend fun FetchNotifications(){
        try {
            val list = _db.collection("blood_requests").get().await()
            if (list != null){
                val notificationList = list.documents.mapNotNull { doc->
                    val data = doc.data
                    data?.let {
                        notification(
                            bloodtype = it["bloodtype"].toString(),
                            hospital = it["hospital"].toString(),
                            date = (it["date"] as? Timestamp)?.let { timestamp ->
                                TimestampToLocalDate(timestamp)
                            }
                        )
                    }
                }.sortedBy { it.date }
                globalNotificationList = notificationList
            }
        }catch (e:Exception){
            errorMessage=e.message.toString()
        }
    }

    suspend fun FetchAnnouncements(){
        try {
            val list = _db.collection("announcements").get().await()
            if (list != null){
                val announcementList = list.documents.mapNotNull { doc->
                    val data = doc.data
                    data?.let {
                        announcement(
                            title = it["title"].toString(),
                            location = it["location"].toString(),
                            dateAndTime = (it["date&time"] as? Timestamp)?.let {timestamp ->
                                TimestampToLocalDateTime(timestamp)
                            }
                        )
                    }
                }.sortedBy { it.dateAndTime }
                globalAnnouncementList = announcementList
            }
        }catch (e:Exception){
            errorMessage=e.message.toString()
        }
    }
}