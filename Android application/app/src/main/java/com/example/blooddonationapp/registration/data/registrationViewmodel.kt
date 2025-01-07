package com.example.blooddonationapp.registration.data

import android.util.Log
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

    fun saveRegistrationEntryByString(entry:String, data:String){
        if (entry == "birthdate"){
            val datamap = mapOf(entry to data)
            if (_auth.currentUser != null){
                _db.collection("userdetails").document(_auth.currentUser!!.uid)
                    .set(datamap, SetOptions.merge())
                    .addOnSuccessListener {
                        //todo
                    }.addOnFailureListener {
                        errorMessage = it.message.toString()
                    }
            }
        }else{
            //todo
        }
    }

    suspend fun getRegistrationType():String{
        Log.d("checkLogin", "inside getRegis")
        try {
            val document = _auth.currentUser?.let { _db.collection("userdetails").document(it.uid) }
                ?.get()?.await()
            if (document != null){
                Log.d("checkLogin", "doc is not null!!!!")
                return document.getString("registration_type").toString()
            }
            else return ""
        }catch (e:Exception){
            errorMessage = e.message.toString()
        }
        return ""
    }
}