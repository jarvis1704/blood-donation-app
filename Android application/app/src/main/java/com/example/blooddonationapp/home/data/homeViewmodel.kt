package com.example.blooddonationapp.home.data

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.blooddonationapp.global.data.errorMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
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
                            date = null
                        )
                    }
                }
                globalNotificationList = notificationList
            }
        }catch (e:Exception){
            errorMessage=e.message.toString()
        }
    }
}