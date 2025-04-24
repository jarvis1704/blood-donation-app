package com.example.blooddonationapp.home.interfaces

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blooddonationapp.AdminEntry.data.newBloodRequest
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.registration.interfaces.AnimatedBloodGroupButton
import com.example.blooddonationapp.registration.interfaces.AnimatedButton

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BloodDonorForm(modifier: Modifier = Modifier) {
    val positiveBloodGroups = listOf("A+", "B+", "AB+", "O+")
    val negativeBloodGroups = listOf("A-", "B-", "AB-", "O-")

    val urgencyTypes = listOf("Emergency", "Within 24 Hours", "Routine")
    val genderTypes = listOf("M", "F", "Others")

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header section with red background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25f)
                    .background(Color(0xFFEB4335))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(50.dp))
                    Text(
                        "Blood Donor Registration",
                        fontSize = 32.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "To become a blood donor, please fill the form",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Main content with white background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.75f)
                    .background(Color.White)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(horizontal = 16.dp)
                        .align(Alignment.Center).offset(y = (-24).dp)
                        .verticalScroll(rememberScrollState()),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(
                            "Donor Information",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFEB4335)
                        )

                        Spacer(Modifier.height(8.dp))
                        Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                        Spacer(Modifier.height(16.dp))

                        Text("Donor Name", fontWeight = FontWeight.Medium)
                        TextField(
                            value = newBloodRequest.patientname,
                            onValueChange = {
                                newBloodRequest.patientname = it
                            },
                            placeholder = { Text("Full Name") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0xFFF5F5F5),
                                focusedContainerColor = Color(0xFFF5F5F5),
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color(0xFFEB4335),
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                disabledPlaceholderColor = Color.LightGray
                            ),
                            singleLine = true
                        )

                        Spacer(Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Donor Age Age", fontWeight = FontWeight.Medium)
                                TextField(
                                    value = newBloodRequest.patientage,
                                    onValueChange = {
                                        newBloodRequest.patientage = it
                                    },
                                    placeholder = { Text("Age") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    colors = TextFieldDefaults.colors(
                                        unfocusedContainerColor = Color(0xFFF5F5F5),
                                        focusedContainerColor = Color(0xFFF5F5F5),
                                        unfocusedIndicatorColor = Color.Transparent,
                                        focusedIndicatorColor = Color(0xFFEB4335),
                                        focusedTextColor = Color.Black,
                                        unfocusedTextColor = Color.Black,
                                        disabledPlaceholderColor = Color.LightGray
                                    ),
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                )
                            }

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Gender", fontWeight = FontWeight.Medium)
                                //selectable gender here
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFFF5F5F5)
                                    ),
                                    elevation = CardDefaults.cardElevation(0.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        FlowRow(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(1.dp),
                                        ) {
                                            genderTypes.forEach { type->
                                                AnimatedButton(
                                                    bloodType = type,
                                                    isSelected = newBloodRequest.patientgender == type,
                                                    onClick = { newBloodRequest.patientgender = type}
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height(24.dp))
                        Text(
                            "Contact Information",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFEB4335)
                        )

                        Spacer(Modifier.height(8.dp))
                        Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                        Spacer(Modifier.height(16.dp))


                        Text("Donor Phone Number", fontWeight = FontWeight.Medium)
                        TextField(
                            value = newBloodRequest.attendantphoneno,
                            onValueChange = {
                                newBloodRequest.attendantphoneno = it
                            },
                            placeholder = { Text("Contact Number") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0xFFF5F5F5),
                                focusedContainerColor = Color(0xFFF5F5F5),
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color(0xFFEB4335),
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                disabledPlaceholderColor = Color.LightGray
                            ),
                            singleLine = true
                        )

                        Spacer(Modifier.height(16.dp))
                        Text("Do you consume any kind of Intoxicants?", fontWeight = FontWeight.Medium)
                        TextField(
                            value = newBloodRequest.hospital,
                            onValueChange = {
                                newBloodRequest.hospital = it
                            },
                            placeholder = { Text("Your Answer") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0xFFF5F5F5),
                                focusedContainerColor = Color(0xFFF5F5F5),
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color(0xFFEB4335),
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                disabledPlaceholderColor = Color.LightGray
                            ),
                            singleLine = true
                        )

                        Spacer(Modifier.height(24.dp))
                        Text(
                            "Blood Details",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFEB4335)
                        )

                        Spacer(Modifier.height(8.dp))
                        Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                        Spacer(Modifier.height(16.dp))

                        Text("Select Blood Group", fontWeight = FontWeight.Medium)
                        Spacer(Modifier.height(8.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF5F5F5)
                            ),
                            elevation = CardDefaults.cardElevation(0.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    positiveBloodGroups.forEach { bloodType ->
                                        AnimatedBloodGroupButton(
                                            bloodType = bloodType,
                                            isSelected = newBloodRequest.bloodgroup == bloodType,
                                            onClick = { newBloodRequest.bloodgroup = bloodType }
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    negativeBloodGroups.forEach { bloodgroup ->
                                        AnimatedBloodGroupButton(
                                            bloodType = bloodgroup,
                                            isSelected = newBloodRequest.bloodgroup == bloodgroup,
                                            onClick = { newBloodRequest.bloodgroup = bloodgroup }
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height(24.dp))
                        Text(
                            "Additional Information (Optional)",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFEB4335)
                        )

                        Spacer(Modifier.height(8.dp))
                        Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                        Spacer(Modifier.height(16.dp))

                        Text("Additional Details", fontWeight = FontWeight.Medium)
                        TextField(
                            value = newBloodRequest.details,
                            onValueChange = {
                                newBloodRequest.details = it
                            },
                            placeholder = { Text("Any relevant information") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .height(120.dp),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0xFFF5F5F5),
                                focusedContainerColor = Color(0xFFF5F5F5),
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color(0xFFEB4335),
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                disabledPlaceholderColor = Color.LightGray
                            )
                        )

                        Spacer(Modifier.height(24.dp))
                        Button(
                            onClick = {
                                TODO("Logic for donor registration")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFEB4335)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                "Submit Donor Request",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}