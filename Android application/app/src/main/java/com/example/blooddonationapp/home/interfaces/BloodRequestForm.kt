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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blooddonationapp.registration.interfaces.AnimatedBloodGroupButton

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BloodRequestForm() {
    // Define blood groups and other selection data
    val positiveBloodGroups = listOf("A+", "B+", "AB+", "O+")
    val negativeBloodGroups = listOf("A-", "B-", "AB-", "O-")
    val urgencyTypes = listOf("Emergency", "Within 24 Hours", "Routine")
    val genderTypes = listOf("M", "F", "Others")

    // Form state management
    var patientName by remember { mutableStateOf("") }
    var patientAge by remember { mutableStateOf("") }
    var patientGender by remember { mutableStateOf("") }
    var attendantName by remember { mutableStateOf("") }
    var attendantPhone by remember { mutableStateOf("") }
    var hospital by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("") }
    var urgencyLevel by remember { mutableStateOf("") }
    var unitsRequired by remember { mutableStateOf("") }
    var additionalDetails by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
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
                        "Blood Request Form",
                        fontSize = 32.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Request Blood Donation",
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
                        .align(Alignment.Center)
                        .offset(y = (-24).dp)
                        .verticalScroll(rememberScrollState()),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        // PATIENT INFORMATION SECTION
                        Text(
                            "Patient Information",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFEB4335)
                        )

                        Spacer(Modifier.height(8.dp))
                        Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                        Spacer(Modifier.height(16.dp))

                        Text("Patient Name", fontWeight = FontWeight.Medium)
                        TextField(
                            value = patientName,
                            onValueChange = { patientName = it },
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
                                Text("Patient Age", fontWeight = FontWeight.Medium)
                                TextField(
                                    value = patientAge,
                                    onValueChange = { patientAge = it },
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
                                    singleLine = true
                                )
                            }

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Gender", fontWeight = FontWeight.Medium)
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
                                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        ) {
                                            genderTypes.forEach { type ->
                                                AnimatedBloodGroupButton(
                                                    bloodType = type,
                                                    isSelected = patientGender == type,
                                                    onClick = { patientGender = type }
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // CONTACT INFORMATION SECTION
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

                        Text("Attendant Name", fontWeight = FontWeight.Medium)
                        TextField(
                            value = attendantName,
                            onValueChange = { attendantName = it },
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
                        Text("Attendant Phone Number", fontWeight = FontWeight.Medium)
                        TextField(
                            value = attendantPhone,
                            onValueChange = { attendantPhone = it },
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
                        Text("Hospital", fontWeight = FontWeight.Medium)
                        TextField(
                            value = hospital,
                            onValueChange = { hospital = it },
                            placeholder = { Text("Hospital Name") },
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

                        // BLOOD REQUIREMENTS SECTION
                        Spacer(Modifier.height(24.dp))
                        Text(
                            "Blood Requirements",
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
                                            isSelected = bloodGroup == bloodType,
                                            onClick = { bloodGroup = bloodType }
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    negativeBloodGroups.forEach { bg ->
                                        AnimatedBloodGroupButton(
                                            bloodType = bg,
                                            isSelected = bloodGroup == bg,
                                            onClick = { bloodGroup = bg }
                                        )
                                    }
                                }
                            }
                        }

                        // ADDITIONAL INFORMATION SECTION
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

                        Text("Urgency Level", fontWeight = FontWeight.Medium)
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
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                ) {
                                    urgencyTypes.forEach { type ->
                                        AnimatedBloodGroupButton(
                                            bloodType = type,
                                            isSelected = urgencyLevel == type,
                                            onClick = { urgencyLevel = type }
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height(16.dp))
                        Text("Units of Blood Required", fontWeight = FontWeight.Medium)
                        TextField(
                            value = unitsRequired,
                            onValueChange = { unitsRequired = it },
                            placeholder = { Text("Number of Units") },
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
                        Text("Additional Details", fontWeight = FontWeight.Medium)
                        TextField(
                            value = additionalDetails,
                            onValueChange = { additionalDetails = it },
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

                        // Display error message if any
                        if (errorMessage.isNotEmpty()) {
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = errorMessage,
                                color = Color.Red,
                                fontSize = 14.sp
                            )
                        }

                        // SUBMIT BUTTON
                        Spacer(Modifier.height(24.dp))
                        Button(
                            onClick = {
                                if (patientName.isNotEmpty() &&
                                    patientAge.isNotEmpty() &&
                                    patientGender.isNotEmpty() &&
                                    attendantName.isNotEmpty() &&
                                    attendantPhone.isNotEmpty() &&
                                    hospital.isNotEmpty() &&
                                    bloodGroup.isNotEmpty() &&
                                    urgencyLevel.isNotEmpty() &&
                                    unitsRequired.isNotEmpty()
                                ) {
                                    // Process submission
                                    // You can call your ViewModel function here
                                } else {
                                    errorMessage = "Please fill all required fields"
                                }
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
                                "Submit Blood Request",
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
