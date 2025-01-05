package com.example.blooddonationapp.global.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

//to keep track of the current user logged in
object currentUser{
    var isSearching by mutableStateOf(true)
    var isLoggedIn by mutableStateOf(false)

    var registrationType by mutableStateOf("")
    /* we fetch this string at runtime
     null = not registered, so goto registration page
     "signup" = only email and password is registered, other data need to be uploaded
     "registered" = goto homepage directly */
}

//for global error dialogue
var errorMessage by mutableStateOf("")         //just change value to push an alert
var isErrorDialogue by mutableStateOf(false)   //no need to change value, launchedAffect takes care of it