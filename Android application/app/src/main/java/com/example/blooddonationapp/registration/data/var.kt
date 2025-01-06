package com.example.blooddonationapp.registration.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

//used to note details until these are saved
object tempRegistrationDetails{
    var birthDate by mutableStateOf("")
    var username by mutableStateOf("")
    var gender by mutableStateOf("")
    var area by mutableStateOf("")
    var phoneNo by mutableStateOf(0)
    var lastDonationDate by mutableStateOf("")
    var locationSelected by mutableStateOf("")
    var bloodGroup by mutableStateOf("")
    var aadharNo by mutableStateOf(0)
    var aadharDOB by mutableStateOf("")
}