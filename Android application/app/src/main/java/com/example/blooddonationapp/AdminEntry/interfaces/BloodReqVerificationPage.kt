package com.example.blooddonationapp.AdminEntry.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.AdminEntry.data.AdminViewModel
import com.example.blooddonationapp.AdminEntry.data.ClearNewBloodReqObj
import com.example.blooddonationapp.AdminEntry.data.bloodreqPendingList
import com.example.blooddonationapp.AdminEntry.data.newBloodRequest
import com.example.blooddonationapp.global.data.NewGlobalAlert
import com.example.blooddonationapp.home.data.bloodRequest

@Composable
fun BloodReqVerificationPage(
    AdminViewmodel: AdminViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        AdminViewmodel.getPendingBloodRequests()
    }

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
                        "Blood Request Verification",
                        fontSize = 32.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Verify pending blood requests",
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
                    .padding(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    bloodreqPendingList?.let {
                        items(it.toList()) { item ->
                            showBloodreq(item)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun showBloodreq(
    request: bloodRequest,
    viewModel: AdminViewModel = hiltViewModel()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                "Patient Details",
                fontWeight = FontWeight.Medium,
                color = Color(0xFF757575),
                fontSize = 14.sp
            )
            Text(
                request.patientname,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                request.patientage,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                request.patientgender,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)

            Text(
                "Attendant Details",
                fontWeight = FontWeight.Medium,
                color = Color(0xFF757575),
                fontSize = 14.sp
            )
            Text(
                request.attendantname,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                request.attendantphoneno,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Hospital",
                fontWeight = FontWeight.Medium,
                color = Color(0xFF757575),
                fontSize = 14.sp
            )
            Text(
                request.hospital,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                "Blood Group",
                fontWeight = FontWeight.Medium,
                color = Color(0xFF757575),
                fontSize = 14.sp
            )
            Text(
                request.bloodtype,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(12.dp))

            //additional data here
            Text(
                "Additional Information",
                fontWeight = FontWeight.Medium,
                color = Color(0xFF757575),
                fontSize = 14.sp
            )
            Text(
                "Urgency level:" + request.urgencylevel,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            if (request.unitsrequired.isNotEmpty()){
                Text(
                    "Units required:" + request.unitsrequired,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            if (request.details.isNotEmpty()){
                Text(
                    request.details,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            // Action buttons
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Button(
                        onClick = {
                            NewGlobalAlert(
                                title = "Push Blood Request",
                                details = "Are you sure you want to push this request globally? This action cannot be undone.",
                                onCancelClick = {},
                                onConfirmClick = {
                                    //push a new blood req, and delete this entry
                                    ClearNewBloodReqObj()
                                    newBloodRequest.patientage = request.patientage
                                    newBloodRequest.patientname = request.patientname
                                    newBloodRequest.patientgender = request.patientgender
                                    newBloodRequest.attendantname = request.attendantname
                                    newBloodRequest.attendantphoneno = request.attendantphoneno
                                    newBloodRequest.hospital = request.hospital
                                    newBloodRequest.bloodgroup = request.bloodtype
                                    newBloodRequest.urgencylevel = request.urgencylevel
                                    newBloodRequest.unitsrequired = request.unitsrequired
                                    newBloodRequest.details = request.details

                                    viewModel.newBloodReq(
                                        onsuccess = {
                                            viewModel.DeletePendingBloodReq(request.id)
                                        }
                                    )
                                }
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEB4335))
                    ) {
                        Text(
                            "Push Blood Request",
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }

                    Button(
                        onClick = {
                            NewGlobalAlert(
                                title = "Delete Blood Request",
                                details = "Are you sure you want to DELETE this request? This action cannot be undone.",
                                onCancelClick = {},
                                onConfirmClick = {
                                    //delete this entry
                                    viewModel.DeletePendingBloodReq(request.id)
                                }
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEB4335))
                    ) {
                        Text(
                            "Delete Request",
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}