package com.example.blooddonationapp.registration.data

import androidx.lifecycle.ViewModel
import com.example.blooddonationapp.global.data.errorMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

class registrationViewmodel : ViewModel(){
    private val _auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val _db : FirebaseFirestore = FirebaseFirestore.getInstance()

    fun saveRegistrationType(type:String, goto_homepage:()->Unit){
        val datamap = mapOf("registration_type" to type)
        if (_auth.currentUser != null){
            _db.collection("userdetails").document(_auth.currentUser!!.uid)
                .set(datamap, SetOptions.merge())
                .addOnSuccessListener {
                    //todo
                    goto_homepage()
                }.addOnFailureListener {
                    errorMessage = it.message.toString()
                }
        }else{
            errorMessage = "Error: No logged in user found"
        }
    }

    suspend fun getRegistrationType():String{
        try {
            val document = _auth.currentUser?.let { _db.collection("userdetails").document(it.uid) }
                ?.get()?.await()
            if (document != null){
                return document.getString("registration_type").toString()
            }
            else return ""
        }catch (e:Exception){
            errorMessage = e.message.toString()
        }
        return ""
    }
}