package com.example.blooddonationapp.auth.data

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class emailLoginViewmodel(): ViewModel() {
    private val _auth : FirebaseAuth = FirebaseAuth.getInstance()

    fun signup(email:String, password:String, confirmpassword:String){
        if (email.isNotEmpty() && password.isNotEmpty()){
            if (password == confirmpassword){
                try {
                    _auth.createUserWithEmailAndPassword(email,password)
                        .addOnSuccessListener {
                            //todo go to homepage
                        }.addOnFailureListener {
                            //todo handle error
                        }
                }catch (e:Exception){
                    //todo handle error
                }
            }else{
                //todo handle error
            }
        }else{
            //todo handle error
        }
    }

    fun login(email: String, password: String){
        if (email.isNotEmpty() && password.isNotEmpty()){
            try {
                _auth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener {
                        //todo go to homepage
                    }.addOnFailureListener {
                        //todo handle error
                    }
            }catch (e:Exception){
                //todo handle error
            }
        }else{
            //todo handle error
        }
    }

    fun signout(){
        _auth.signOut()
        //todo clear temp user data and goto login page
    }
}