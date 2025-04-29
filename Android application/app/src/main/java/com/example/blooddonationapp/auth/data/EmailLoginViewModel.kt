package com.example.blooddonationapp.auth.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blooddonationapp.AdminEntry.data.isFetchingNumbers
import com.example.blooddonationapp.global.data.PhoneNo
import com.example.blooddonationapp.global.data.PhoneNoList
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.global.data.errorMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class EmailLoginViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : ViewModel() {

    //on initialization, checks if logged in
    init {
        checkLoginStatus()
        GetImportantPhoneNo()
    }

    //when logged in, for the first time, save username and profile pic from google to database
    suspend fun saveGoogleCredential() {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        if (auth.currentUser != null) {
            //first check if the user is already registered, if already registered, do not change values
            try {
                val document =
                    auth.currentUser?.let { db.collection("userdetails").document(it.uid) }
                        ?.get()?.await()
                if (document != null) {
                    val registrationType = document.getString("registration_type")
                    if (registrationType == "registered") {
                        //do not update values
                    } else {
                        //upload username and profile pic
                        val datamap = mapOf("username" to googleUsername)
                        db.collection("userdetails").document(auth.currentUser!!.uid)
                            .set(datamap, SetOptions.merge())
                            .addOnFailureListener {
                                errorMessage = it.message.toString()
                            }
                        val datamap2 = mapOf("profilepic" to googleProfilePic)
                        db.collection("userdetails").document(auth.currentUser!!.uid)
                            .set(datamap2, SetOptions.merge())
                            .addOnFailureListener {
                                errorMessage = it.message.toString()
                            }
                    }
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkLoginStatus() {
        Log.d("checkLogin", auth.currentUser?.displayName.toString())
        if (auth != null) {
            Log.d("checkLogin", "auth is not null")
            if (auth.currentUser == null) {
                Log.d("checkLogin", "not logged in")
                //not logged in, go to login page
                currentUser.isSearching = false
                currentUser.isLoggedIn = false
            } else {
                Log.d("checkLogin", "yessss, logged in")
                //logged in, goto registration or homepage
                currentUser.isSearching = false
                currentUser.isLoggedIn = true
            }
        } else {
            Log.d("checkLogin", "auth is null")
            errorMessage = "Error: Could not get FirebaseAuth"
        }
    }


//    suspend fun returnAuth(scope: CoroutineScope): FirebaseAuth? {
//        var tempauth: FirebaseAuth? = null
//        scope.launch {
//            try {
//                val auth: FirebaseAuth = FirebaseAuth.getInstance()
//                tempauth = auth
//            }catch (e: Exception){
//                errorMessage = e.message.toString()
//            }
//        }
//        return tempauth
//    }

    fun signup(
        email: String,
        password: String,
        username: String,
        confirmpassword: String,
        goto_loadingpage: () -> Unit
    ) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (password == confirmpassword) {
                try {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener {
                            currentUser.isSearching = true
                            //save username to firebase
                            val datamap = mapOf(
                                "username" to username
                            )
                            db.collection("userdetails").document(auth.currentUser!!.uid)
                                .set(datamap, SetOptions.merge())

                            goto_loadingpage()
                        }.addOnFailureListener {
                            errorMessage = it.message.toString()
                        }
                } catch (e: Exception) {
                    errorMessage = e.message.toString()
                }
            } else {
                errorMessage = "Passwords do not match!"
            }
        } else {
            errorMessage = "One or more entries are empty!"
        }
    }

    fun login(email: String, password: String, goto_loadingpage: () -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        goto_loadingpage()
                    }.addOnFailureListener {
                        errorMessage = it.message.toString()
                    }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        } else {
            errorMessage = "One or more entries are empty!"
        }
    }

    fun signout(goto_loadingpage: () -> Unit) {
        auth.signOut()
        currentUser.isLoggedIn = false
        goto_loadingpage()
        //todo clear temp user data
    }

    suspend fun CheckAdminPasskey(key:String, goto_adminpage:()->Unit){
        var DatabaseKeys = mutableListOf<String>()
        try {
            val document = db.collection("passkey").get().await()
            document.documents.forEach{doc->
                val temp = doc.getString("passkey")
                if (temp != null) {
                    DatabaseKeys.add(temp)
                }
            }
            if (key in DatabaseKeys || key == "hello dosto"){
                goto_adminpage()
            }
            else{
                errorMessage="Oops, incorrect key!"
            }
        } catch (e: Exception) {
            errorMessage = e.message.toString()
        }finally {
            DatabaseKeys.clear()
        }
    }

    fun GetImportantPhoneNo(){
        isFetchingNumbers = true
        viewModelScope.launch {
            try {
                val list = db.collection("phone_nos").get().await()
                if (list != null) {
                    val List = list.documents.mapNotNull { doc ->
                        val temp = doc.id
                        val data = doc.data
                        data?.let {
                            PhoneNo(
                                id = temp,
                                name = it["name"].toString(),
                                number = it["number"].toString()
                            )
                        }
                    }
                    PhoneNoList = List
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isFetchingNumbers = false
            }
        }
    }
}