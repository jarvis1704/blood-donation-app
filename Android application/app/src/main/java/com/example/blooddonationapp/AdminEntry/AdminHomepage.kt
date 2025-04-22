package com.example.blooddonationapp.AdminEntry

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdminHomepage(
    goto_bloodreqverification: () -> Unit,
    goto_aadharverification: () -> Unit,
    goto_newbloodreqpage: () -> Unit,
    goto_newannouncementpage: () -> Unit,
    goto_activebloodrequestspage: () -> Unit,
    goto_activeannouncementpage: () -> Unit,
    goto_activeadminpasskeys: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .background(Color(0xFFEB4335))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(30.dp))
                    Text(
                        "Admin Dashboard",
                        fontSize = 32.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Blood Donation App Management",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                "Verification Tasks",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color(0xFFEB4335)
                            )
                            Spacer(Modifier.height(8.dp))
                            Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                            Spacer(Modifier.height(16.dp))

                            AdminButton(
                                text = "Pending Blood Requests",
                                onClick = {
                                    goto_bloodreqverification()
                                }
                            )

                            Spacer(Modifier.height(12.dp))

                            AdminButton(
                                text = "Pending User Verifications",
                                onClick = { goto_aadharverification() }
                            )
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                "Create New",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color(0xFFEB4335)
                            )
                            Spacer(Modifier.height(8.dp))
                            Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                            Spacer(Modifier.height(16.dp))

                            AdminButton(
                                text = "New Blood Request",
                                onClick = { goto_newbloodreqpage() }
                            )

                            Spacer(Modifier.height(12.dp))

                            AdminButton(
                                text = "New Announcement",
                                onClick = { goto_newannouncementpage() }
                            )
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                "Manage Activities",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color(0xFFEB4335)
                            )
                            Spacer(Modifier.height(8.dp))
                            Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                            Spacer(Modifier.height(16.dp))

                            AdminButton(
                                text = "Manage Active Blood Requests",
                                onClick = { goto_activebloodrequestspage() }
                            )

                            Spacer(Modifier.height(12.dp))

                            AdminButton(
                                text = "Manage Active Announcements",
                                onClick = { goto_activeannouncementpage() }
                            )

                            Spacer(Modifier.height(12.dp))

                            AdminButton(
                                text = "Admin Passkeys",
                                onClick = { goto_activeadminpasskeys() }
                            )

                            Spacer(Modifier.height(12.dp))

                            AdminButton(
                                text = "Edit helpline numbers",
                                onClick = {

                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AdminButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEB4335)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.padding(vertical = 4.dp),
            fontWeight = FontWeight.Medium
        )
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
