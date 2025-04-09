package com.example.blooddonationapp.home.data

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

data class bloodRequest(
    var id:String,
    var patientname: String,
    var patientage : String,
    var patientgender : String,
    var attendantname: String,
    var attendantphoneno: String,
    var bloodtype:String,
    var hospital: String,
    var urgencylevel : String,
    var unitsrequired : String,
    var details : String,
    var date: LocalDateTime?
)

var globalBloodRequestList: List<bloodRequest>? by mutableStateOf(null)

data class notification(
    var type:String,
    var title: String?,
    val bloodtype: String?,
    var body: String?,
    var location: String?,
    var dateAndTime: LocalDateTime?
)

var globalNotificationList: List<notification>? by mutableStateOf(null)
var newNotificationsCounter :Int by mutableIntStateOf(0)

@SuppressLint("NewApi")
fun TimestampToLocalDate(timestamp: Timestamp):LocalDate{
    return timestamp.toDate().toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}

@SuppressLint("NewApi")
fun TimestampToLocalDateTime(timestamp: Timestamp):LocalDateTime{
    return timestamp.toDate().toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
}

data class announcement(
    var id: String,
    var title:String,
    var location: String,
    var dateAndTime: LocalDateTime?
)

var globalAnnouncementList: List<announcement>? by mutableStateOf(null)
