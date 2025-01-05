package com.example.blooddonationapp.auth.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

//these 3 for google auth
data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)
data class UserData(
    val userId: String,
    val username: String?
)


//to temporarily store the data in login and signup page
object tempUserObj{
    var email by mutableStateOf("")
    var password by mutableStateOf("")
}


var isPasswordShown by mutableStateOf(false)  //toggles on "show password click"