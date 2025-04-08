package com.example.blooddonationapp.AdminEntry

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.registration.ui_components.dateYearSelector
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewAnnouncement(
    adminViewmodel: AdminViewmodel = hiltViewModel()
){
    Column (
        modifier = Modifier.padding(16.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        Text("New Announcement")
        Spacer(Modifier.height(16.dp))
        Text("Title")
        TextField(
            value = newAnnouncement.title,
            onValueChange = {
                newAnnouncement.title = it
            }
        )
        Text("Location")
        TextField(
            value = newAnnouncement.location,
            onValueChange = {
                newAnnouncement.location = it
            }
        )
        Text("Select date and time")
        var tempDateState = remember { mutableStateOf(newAnnouncement.date) }
        LaunchedEffect(tempDateState.value) {
            newAnnouncement.date = tempDateState.value
        }
        dateYearSelector(
            dateToBeUpdated = tempDateState,
            selectedDate = LocalDate.now()
        )

        var TempTime = remember { mutableStateOf(newAnnouncement.time) }
        LaunchedEffect(TempTime.value) {
            newAnnouncement.time = TempTime.value
        }
        TimeSelector(toUpdate = TempTime)
        Button(
            onClick = { showDialog = true })
        {
            newAnnouncement.time?.let {
                Text(it.format(DateTimeFormatter.ofPattern("hh : mm a")),
            ) }
        }
        Button(
            onClick = {
                adminViewmodel.newAnnouncement()
            }
        ) {
            Text("Push")
        }
    }
}