package com.example.blooddonationapp.home.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate

data class notification(
    var bloodtype:String,
    var hospital: String,
    var date : LocalDate?
)

var globalNotificationList: List<notification>? by mutableStateOf(null)