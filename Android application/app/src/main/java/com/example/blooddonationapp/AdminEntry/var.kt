package com.example.blooddonationapp.AdminEntry

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate
import java.time.LocalTime

object newBloodRequest{
    var patientname by mutableStateOf("")
    var patientage by mutableStateOf("")
    var patientgender by mutableStateOf("")
    var attendantname by mutableStateOf("")
    var attendantphoneno by mutableStateOf("")
    var hospital by mutableStateOf("")
    var bloodgroup by mutableStateOf("")
    //these 3 are optional
    var urgencylevel by mutableStateOf("")
    var unitsrequired by mutableStateOf("")
    var details by mutableStateOf("")
}

@SuppressLint("NewApi")
object newAnnouncement{
    var title by mutableStateOf("")
    var location by mutableStateOf("")
    var date : LocalDate by mutableStateOf(LocalDate.now())
    var time : LocalTime? by mutableStateOf(LocalTime.now())
}

data class aadharUser(
    var useremail:String,
    var aadharStatus : String,
    var aadharNo : Long?,
    var aadharDOB: Long?,
    var aadharPhotoString: String
)

var aadharPendingList : List<aadharUser>? by mutableStateOf(null)
var showDialog by mutableStateOf(false) // for time selector
var ActivePasskeysList by mutableStateOf(listOf<Passkey>())
var isFetchingPasskeys by mutableStateOf(false)

data class Passkey(
    var id: String,
    var key: String
)

var isNewPasskeyDialogue by mutableStateOf(false)
var tempNewPasskey by mutableStateOf("")