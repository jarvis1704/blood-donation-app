package com.example.blooddonationapp.AdminEntry

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.blooddonationapp.home.data.bloodRequest
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
    var urgencylevel by mutableStateOf("Emergency")
    var unitsrequired by mutableStateOf("")
    var details by mutableStateOf("")
}

fun ClearNewBloodReqObj(){
    newBloodRequest.patientname = ""
    newBloodRequest.patientage = ""
    newBloodRequest.patientgender = ""
    newBloodRequest.attendantname = ""
    newBloodRequest.attendantphoneno = ""
    newBloodRequest.hospital = ""
    newBloodRequest.bloodgroup = ""
    newBloodRequest.unitsrequired = ""
    newBloodRequest.urgencylevel = "Emergency"
    newBloodRequest.details = ""
}

@SuppressLint("NewApi")
object newAnnouncement{
    var title by mutableStateOf("")
    var location by mutableStateOf("")
    var date : LocalDate by mutableStateOf(LocalDate.now())
    var time : LocalTime? by mutableStateOf(LocalTime.now())
}

@RequiresApi(Build.VERSION_CODES.O)
fun ClearNewAnnouncementObj(){
    newAnnouncement.title = ""
    newAnnouncement.location = ""
    newAnnouncement.date = LocalDate.now()
    newAnnouncement.time = LocalTime.now()
}

data class aadharUser(
    var useremail:String,
    var aadharStatus : String,
    var aadharNo : Long?,
    var aadharDOB: Long?,
    var aadharPhotoString: String
)

var aadharPendingList : List<aadharUser>? by mutableStateOf(null)
var bloodreqPendingList : List<bloodRequest>? by mutableStateOf(null)

var showDialog by mutableStateOf(false) // for time selector
var ActivePasskeysList by mutableStateOf(listOf<Passkey>())
var isFetchingPasskeys by mutableStateOf(false)

data class Passkey(
    var id: String,
    var key: String
)

var isNewPasskeyDialogue by mutableStateOf(false)
var tempNewPasskey by mutableStateOf("")