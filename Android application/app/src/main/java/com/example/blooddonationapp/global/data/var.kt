package com.example.blooddonationapp.global.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

//to keep track of the current user logged in
object currentUser{
    var isSearching by mutableStateOf(true)
    var isLoggedIn by mutableStateOf(false)
}

//this var stores errors, just change it to push an error dialogue containing the message
var errorMessage by mutableStateOf("")
var isErrorDialogue by mutableStateOf(false)