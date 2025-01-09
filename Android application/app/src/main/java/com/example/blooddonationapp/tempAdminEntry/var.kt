package com.example.blooddonationapp.tempAdminEntry

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