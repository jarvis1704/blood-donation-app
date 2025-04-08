package com.example.blooddonationapp.AdminEntry

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalTime

@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdminHomepage(
    goto_aadharverification:()->Unit,
    goto_newbloodreqpage:()->Unit,
    goto_newannouncementpage:()->Unit,
    goto_activebloodrequestspage:()->Unit
){
    Box(modifier = Modifier.padding(16.dp)){
        Column (

        ){
            Spacer(Modifier.height(32.dp))
            Text("Welcome Admin")
            Spacer(Modifier.height(16.dp))
            Text("Verify")
            Button(
                onClick = {}
            ) {
                Text("Pending Blood Requests")
            }
            Button(
                onClick = {
                    goto_aadharverification()
                }
            ) {
                Text("Pending User Verifications")
            }
            Text("New")
            Button(
                onClick = {
                    goto_newbloodreqpage()
                }
            ) {
                Text("New Blood Request")
            }
            Button(
                onClick = {
                    goto_newannouncementpage()
                }
            ) {
                Text("New Announcement")
            }
            Text("Edit")
            Button(
                onClick = {
                    goto_activebloodrequestspage()
                }
            ) {
                Text("Manage Active Blood requests")
            }
            Button(
                onClick = {}
            ) {
                Text("Manage Active Announcements")
            }
            Button(
                onClick = {}
            ) {
                Text("Manage Admin Passkeys")
            }
        }
    }
//    var viewmodel:adminViewmodel = viewModel()
//    CoroutineScope(Dispatchers.IO).launch {
//        viewmodel.getPendingAadhar()
//    }
//    var temppage by remember { mutableStateOf("adminpage") }
//    when(temppage){
//        "adminpage"->{
//            Column (
//                modifier = Modifier.fillMaxSize().padding(50.dp).verticalScroll(rememberScrollState()),
//            ){
//                Button(
//                    onClick = {
//                        temppage = "aadhar"
//                    }
//                ) {
//                    Text("Verify pending aadhar->")
//                }
//                Text("new blood req:")
//                TextField(
//                    value = newBloodRequest.bloodgroup,
//                    onValueChange = {
//                        newBloodRequest.bloodgroup = it
//                    }, placeholder = {
//                        Text("Blood group")
//                    })
//                TextField(
//                    value = newBloodRequest.hospital,
//                    onValueChange = {
//                        newBloodRequest.hospital = it
//                    }, placeholder = {
//                        Text("Hospital")
//                    })
//                TextField(
//                    value = newBloodRequest.details,
//                    onValueChange = {
//                        newBloodRequest.details = it
//                    }, placeholder = {
//                        Text("details (optional)")
//                    })
//                Button(
//                    onClick = {
//                        if (newBloodRequest.bloodgroup != "" && newBloodRequest.hospital != ""){
//                            viewmodel.newBloodReq()
////                            viewmodel.newNotification(type ="bloodrequest", bloodtype = newBloodRequest.bloodgroup, location = newBloodRequest.hospital)
//                        }
//                        else{
//                            errorMessage = "Error: multiple entries are empty"
//                        }
//                    }
//                ) {
//                    Text("Push")
//                }
//                Text("new announcement:")
//                TextField(
//                    value = newAnnouncement.title,
//                    onValueChange = {
//                        newAnnouncement.title = it
//                    }, placeholder = {
//                        Text("Title")
//                    })
//                TextField(
//                    value = newAnnouncement.location,
//                    onValueChange = {
//                        newAnnouncement.location = it
//                    }, placeholder = {
//                        Text("Location")
//                    })
//                Text("Select date:")
//                var tempDateState = remember { mutableStateOf(newAnnouncement.date) }
//                LaunchedEffect(tempDateState.value) {
//                    newAnnouncement.date = tempDateState.value
//                }
//                dateYearSelector(
//                    dateToBeUpdated = tempDateState,
//                    selectedDate = LocalDate.now()
//                )
//                SimpleTimeSelector12Hour()
//                Text("time:"+newAnnouncement.time)
//                Button(
//                    onClick = {
//                        if (newAnnouncement.title != "" && newAnnouncement.location != ""){
//                            //todo combine date and time
//                            viewmodel.newAnnouncement()
////                            viewmodel.newNotification(type ="announcement", location = newAnnouncement.location, title = newAnnouncement.title)
//                        }
//                        else{
//                            errorMessage = "Error: multiple entries are empty"
//                        }
//                    }
//                ) {
//                    Text("Push")
//                }
//            }
//        }
//        "aadhar"->{
//            verifyaadhar()
//        }
//    }

}