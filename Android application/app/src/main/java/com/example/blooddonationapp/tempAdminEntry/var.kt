package com.example.blooddonationapp.tempAdminEntry

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
object newBloodRequest{
    var details by mutableStateOf("")
    var hospital by mutableStateOf("")
    var bloodgroup by mutableStateOf("")
}

@SuppressLint("NewApi")
object newAnnouncement{
    var title by mutableStateOf("")
    var location by mutableStateOf("")
    var date : LocalDate by mutableStateOf(LocalDate.now())
    var time : LocalTime by mutableStateOf(LocalTime.now())
}

data class aadharUser(
    var useremail:String,
    var aadharStatus : String,
    var aadharNo : Long?,
    var aadharDOB: Long?,
    var aadharPhotoString: String
)

var aadharPendingList : List<aadharUser>? by mutableStateOf(null)