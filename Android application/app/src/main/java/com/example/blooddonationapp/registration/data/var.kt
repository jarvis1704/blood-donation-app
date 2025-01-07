package com.example.blooddonationapp.registration.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate

//used to note details until these are saved
@RequiresApi(Build.VERSION_CODES.O)
object tempRegistrationDetails{
    var birthDate by mutableStateOf(LocalDate.now())
    var username by mutableStateOf("")
    var gender by mutableStateOf("")
    var area by mutableStateOf("")
    var phoneNo by mutableStateOf("")
    var lastDonationDate : LocalDate? by mutableStateOf(null)
    var locationSelected by mutableStateOf("")
    var bloodGroup by mutableStateOf("")
    var aadharNo : Long? by mutableStateOf(null)
    var aadharDOB by mutableStateOf("")
}