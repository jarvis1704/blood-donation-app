package com.example.blooddonationapp.tempAdminEntry

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.registration.ui_components.dateYearSelector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdminPannel(){
    var viewmodel:adminViewmodel = viewModel()
    CoroutineScope(Dispatchers.IO).launch {
        viewmodel.getPendingAadhar()
    }
    var temppage by remember { mutableStateOf("adminpage") }
    when(temppage){
        "adminpage"->{
            Column (
                modifier = Modifier.fillMaxSize().padding(50.dp).verticalScroll(rememberScrollState()),
            ){
                Button(
                    onClick = {
                        temppage = "aadhar"
                    }
                ) {
                    Text("Verify pending aadhar->")
                }
                Text("new blood req:")
                TextField(
                    value = newBloodRequest.bloodgroup,
                    onValueChange = {
                        newBloodRequest.bloodgroup = it
                    }, placeholder = {
                        Text("Blood group")
                    })
                TextField(
                    value = newBloodRequest.hospital,
                    onValueChange = {
                        newBloodRequest.hospital = it
                    }, placeholder = {
                        Text("Hospital")
                    })
                TextField(
                    value = newBloodRequest.details,
                    onValueChange = {
                        newBloodRequest.details = it
                    }, placeholder = {
                        Text("details (optional)")
                    })
                Button(
                    onClick = {
                        if (newBloodRequest.bloodgroup != "" && newBloodRequest.hospital != ""){
                            viewmodel.newBloodReq()
//                            viewmodel.newNotification(type ="bloodrequest", bloodtype = newBloodRequest.bloodgroup, location = newBloodRequest.hospital)
                        }
                        else{
                            errorMessage = "Error: multiple entries are empty"
                        }
                    }
                ) {
                    Text("Push")
                }
                Text("new announcement:")
                TextField(
                    value = newAnnouncement.title,
                    onValueChange = {
                        newAnnouncement.title = it
                    }, placeholder = {
                        Text("Title")
                    })
                TextField(
                    value = newAnnouncement.location,
                    onValueChange = {
                        newAnnouncement.location = it
                    }, placeholder = {
                        Text("Location")
                    })
                Text("Select date:")
                var tempDateState = remember { mutableStateOf(newAnnouncement.date) }
                LaunchedEffect(tempDateState.value) {
                    newAnnouncement.date = tempDateState.value
                }
                dateYearSelector(
                    dateToBeUpdated = tempDateState,
                    selectedDate = LocalDate.now()
                )
                SimpleTimeSelector12Hour()
                Text("time:"+newAnnouncement.time)
                Button(
                    onClick = {
                        if (newAnnouncement.title != "" && newAnnouncement.location != ""){
                            //todo combine date and time
                            viewmodel.newAnnouncement()
//                            viewmodel.newNotification(type ="announcement", location = newAnnouncement.location, title = newAnnouncement.title)
                        }
                        else{
                            errorMessage = "Error: multiple entries are empty"
                        }
                    }
                ) {
                    Text("Push")
                }
            }
        }
        "aadhar"->{
            verifyaadhar()
        }
    }

}

@SuppressLint("NewApi")
@Composable
fun SimpleTimeSelector12Hour() {
    var hour by remember { mutableStateOf("12") }
    var minute by remember { mutableStateOf("00") }
    var isAm by remember { mutableStateOf(true) } // AM or PM toggle

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Select Time",)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Hour Input
            TextField(
                value = hour,
                onValueChange = { input ->
                    hour = input.filter { it.isDigit() }.take(2).takeIf { it.toIntOrNull() in 1..12 } ?: hour
                },
                label = { Text("Hour") },
                singleLine = true,
                modifier = Modifier.width(80.dp)
            )

            Text(":")

            // Minute Input
            TextField(
                value = minute,
                onValueChange = { input ->
                    minute = input.filter { it.isDigit() }.take(2).takeIf { it.toIntOrNull() in 0..59 } ?: minute
                },
                label = { Text("Minute") },
                singleLine = true,
                modifier = Modifier.width(80.dp)
            )

            // AM/PM Toggle
            Button(onClick = { isAm = !isAm }) {
                Text(if (isAm) "AM" else "PM")
            }
        }

        // Confirm Button
        Button(onClick = {
            // Convert to LocalTime
            val hourInt = hour.toIntOrNull() ?: 12
            val minuteInt = minute.toIntOrNull() ?: 0
            val adjustedHour = if (isAm) {
                if (hourInt == 12) 0 else hourInt // 12 AM is 0, 1-11 AM stays the same
            } else {
                if (hourInt == 12) 12 else hourInt + 12 // 12 PM is 12, 1-11 PM adds 12
            }

            val selectedTime = LocalTime.of(adjustedHour, minuteInt)
            println("Selected LocalTime: $selectedTime") // Logs LocalTime
            newAnnouncement.time = selectedTime
        }) {
            Text("Confirm Time")
        }
    }
}
