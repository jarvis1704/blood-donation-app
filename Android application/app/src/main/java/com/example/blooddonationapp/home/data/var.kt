package com.example.blooddonationapp.home.data

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.Timestamp
import java.sql.Time
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

data class notification(
    var bloodtype:String,
    var hospital: String,
    var date: LocalDateTime?
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
    var title:String,
    var location: String,
    var dateAndTime: LocalDateTime?
)

var globalAnnouncementList: List<announcement>? by mutableStateOf(null)
