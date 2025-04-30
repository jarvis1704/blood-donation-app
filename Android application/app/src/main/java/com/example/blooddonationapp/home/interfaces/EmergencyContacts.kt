package com.example.blooddonationapp.home.interfaces

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.example.blooddonationapp.global.data.PhoneNoList
import com.example.blooddonationapp.global.data.updateCurrentUser


// Define color constants for consistency
private val primaryRed = Color(0xFFEB4335)
private val lightRed = Color(0xFFF5948C)
private val paleRed = Color(0xFFFAD5D1)
private val backgroundColor = Color.White

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EmergencyContacts(
    navController: NavController
) {
    val context = LocalContext.current
    updateCurrentUser()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize()
        ) {
            // Header section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f)
                    .background(primaryRed)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 80.dp, start = 16.dp)
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Back Button",
                            tint = Color.White
                        )
                    }
                    Text(
                        "Emergency Contacts",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Content section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.85f)
                    .background(primaryRed)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                        .background(backgroundColor)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 20.dp, vertical = 24.dp)
                ) {
                    // Info card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = paleRed
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                "Important Information",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = primaryRed
                            )
                            Text(
                                "These emergency contacts are available 24/7 to assist with blood donation needs. Please call the most appropriate contact for your situation.",
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }

                    // Emergency contacts section
                    if (PhoneNoList.isNotEmpty()) {
//                        EmergencyContactsSection("Blood Bank Contacts")
                        PhoneNoList.forEach { contact ->
                            EmergencyContactCard(
                                name = contact.name,
                                phoneNumber = contact.number,
                                context = context
                            )
                        }
                    } else {
                        NoContactsAvailable()
                    }

//                    // Hospital contacts section
//                    EmergencyContactsSection("Hospital Emergency Contacts")
//                    EmergencyContactCard(
//                        name = "Tezpur Medical College",
//                        phoneNumber = "+91 3712 267888"
//                    )
//                    EmergencyContactCard(
//                        name = "Tezpur Civil Hospital",
//                        phoneNumber = "+91 3712 220101"
//                    )
//
//                    // Red Cross Society contacts section
//                    EmergencyContactsSection("Red Cross Society")
//                    EmergencyContactCard(
//                        name = "Tezpur Red Cross Office",
//                        phoneNumber = "+91 3712 220022"
//                    )
//                    EmergencyContactCard(
//                        name = "Blood Donation Coordinator",
//                        phoneNumber = "+91 9876543210"
//                    )
//
                    // Help text at bottom
                    Text(
                        "In case of emergency, please call the nearest blood bank or hospital first",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )

                    Spacer(Modifier.height(100.dp))
                }
            }
        }
    }
}

@Composable
fun EmergencyContactsSection(title: String) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = primaryRed,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Divider(
            color = paleRed,
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
fun EmergencyContactCard(name: String, phoneNumber: String, context: Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Contact avatar
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(lightRed),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Contact",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            // Contact details
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = phoneNumber,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Call icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(primaryRed)
                    .clickable(
                        onClick = {
                            val launchPhone = Intent(Intent.ACTION_DIAL)
                            launchPhone.data = "tel:$phoneNumber".toUri()
                            context.startActivity(launchPhone)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Call",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun NoContactsAvailable() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "No emergency contacts available at the moment",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
            Text(
                "Please check back later",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}