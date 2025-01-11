package com.example.blooddonationapp.tempAdminEntry

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.registration.data.tempRegistrationDetails
import com.example.blooddonationapp.registration.ui_components.dateYearSelector
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun adminPage(){
    var viewmodel:adminViewmodel = viewModel()
    Column (
        modifier = Modifier.fillMaxSize().padding(50.dp),
    ){
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
                    viewmodel.newNotification()
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
        Button(
            onClick = {
                if (newAnnouncement.title != "" && newAnnouncement.location != ""){
                    //todo combine date and time
                    viewmodel.newAnnouncement()
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