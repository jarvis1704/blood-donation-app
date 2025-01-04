package com.example.blooddonationapp.auth.data

import androidx.lifecycle.ViewModel
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

    fun signout(goto_loadingpage:()->Unit){
        _auth.signOut()
        goto_loadingpage()
        //todo clear temp user data
    }
}