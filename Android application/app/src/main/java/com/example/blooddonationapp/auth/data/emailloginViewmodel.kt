package com.example.blooddonationapp.auth.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.global.data.errorMessage
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class emailLoginViewmodel(): ViewModel() {
    private val _auth : FirebaseAuth = FirebaseAuth.getInstance()

    //on initialization, checks if logged in

    init {
        checkLoginStatus()
    }

    fun checkLoginStatus(){
        val auth = FirebaseAuth.getInstance()
        Log.d("checkLogin", auth.currentUser?.displayName.toString())
        if (auth != null){
            Log.d("checkLogin", "auth is not null")
            if(auth.currentUser==null){
                Log.d("checkLogin", "not logged in")
                //not logged in, go to login page
                currentUser.isSearching=false
                currentUser.isLoggedIn=false
            }else{
                Log.d("checkLogin", "yessss, logged in")
                //logged in, goto registration or homepage
                currentUser.isSearching=false
                currentUser.isLoggedIn=true
            }
        }else{
            Log.d("checkLogin", "auth is null")
            errorMessage = "Error: Could not get FirebaseAuth"
        }
    }



    suspend fun returnAuth(scope: CoroutineScope):FirebaseAuth?{
        var tempauth: FirebaseAuth? = null
//        scope.launch {
//            try {
//                val auth: FirebaseAuth = FirebaseAuth.getInstance()
//                tempauth = auth
//            }catch (e: Exception){
//                errorMessage = e.message.toString()
//            }
//        }
        return tempauth
    }

    fun signup(email:String, password:String, confirmpassword:String, goto_loadingpage: () -> Unit){
        if (email.isNotEmpty() && password.isNotEmpty()){
            if (password == confirmpassword){
                try {
                    _auth.createUserWithEmailAndPassword(email,password)
                        .addOnSuccessListener {
                            currentUser.isSearching = true
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

    open fun login(email: String, password: String, goto_loadingpage:()->Unit){
        if (email.isNotEmpty() && password.isNotEmpty()){
            try {
                _auth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener {
                        goto_loadingpage()
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