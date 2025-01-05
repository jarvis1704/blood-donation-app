package com.example.blooddonationapp.auth.data

import androidx.lifecycle.ViewModel
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.global.data.errorMessage
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class emailLoginViewmodel(): ViewModel() {
    private val _auth : FirebaseAuth = FirebaseAuth.getInstance()

    //on initialization, checks if logged in
    init {
        CoroutineScope(Dispatchers.Main).launch {
            while (true){
                val auth: FirebaseAuth = FirebaseAuth.getInstance()
                if(auth.currentUser==null){
                    //not logged in, go to login page
                    currentUser.isSearching=false
                    currentUser.isLoggedIn=false
                }else{
                    //logged in, goto homepage
                    currentUser.isSearching=false
                    currentUser.isLoggedIn=true
                }
                delay(3000)
            }
        }
    }

    fun signup(email:String, password:String, confirmpassword:String, goto_loadingpage: () -> Unit){
        if (email.isNotEmpty() && password.isNotEmpty()){
            if (password == confirmpassword){
                try {
                    _auth.createUserWithEmailAndPassword(email,password)
                        .addOnSuccessListener {
                            currentUser.isSearching=true
                            goto_loadingpage()
                        }.addOnFailureListener {
                            errorMessage = it.message.toString()
                        }
                }catch (e:Exception){
                    errorMessage = e.message.toString()
                }
            }else{
                errorMessage = "Passwords do not match!"
            }
        }else{
            errorMessage = "One or more entries are empty!"
        }
    }

    fun login(email: String, password: String, goto_homepage:()->Unit){
        if (email.isNotEmpty() && password.isNotEmpty()){
            try {
                _auth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener {
                        goto_homepage()
                    }.addOnFailureListener {
                        errorMessage = it.message.toString()
                    }
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }else{
            errorMessage = "One or more entries are empty!"
        }
    }

    fun signout(goto_loadingpage:()->Unit){
        _auth.signOut()
        currentUser.isLoggedIn = false
        goto_loadingpage()
        //todo clear temp user data
    }
}