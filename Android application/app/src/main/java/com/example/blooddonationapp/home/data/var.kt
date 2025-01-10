package com.example.blooddonationapp.home.data

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.Timestamp
import java.time.LocalDate
import java.time.ZoneId

data class notification(
    var bloodtype:String,
    var hospital: String,
    var date: LocalDate?
)

var globalNotificationList: List<notification>? by mutableStateOf(null)

@SuppressLint("NewApi")
fun TimestampToLocalDate(timestamp: Timestamp):LocalDate{
    return timestamp.toDate().toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}