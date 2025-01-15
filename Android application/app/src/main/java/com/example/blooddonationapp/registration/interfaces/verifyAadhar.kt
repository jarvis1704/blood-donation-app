package com.example.blooddonationapp.registration.interfaces

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.global.data.checkCorrectDateStringEntered
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.registration.data.ProcessImage
import com.example.blooddonationapp.registration.data.RegistrationViewModel
import com.example.blooddonationapp.registration.data.photoUploadStatus
import com.example.blooddonationapp.registration.data.tempRegistrationDetails
import com.example.blooddonationapp.registration.ui_components.imagePicker


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun verifyAadhar(
    goto_homepage: () -> Unit,
    registrationViewModel: RegistrationViewModel = hiltViewModel()
) {
//    var registrationViewModel: RegistrationViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .background(Color(0xFFEB4335))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(vertical = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "Verify Aadhar",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        "Please Enter your Aadhar Details for verification",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .background(Color.White)
            ){
                Column(
                    modifier = Modifier.fillMaxSize().offset(y = (-100).dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    //card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(horizontal = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        ) {
                            Text(text = "Aadhar Number", fontWeight = FontWeight.Medium)
                            TextField(
                                value = tempRegistrationDetails.aadharNo?.toString()?:"",
                                onValueChange = {
                                    val digitsOnly =
                                        it.filter { char -> char.isDigit() }  //filters only numerical digits
                                    val limitedDigits =
                                        if (digitsOnly.length > 16) {        //limited to 10 digits
                                            digitsOnly.substring(0, 16)
                                        } else {
                                            digitsOnly
                                        }
                                    tempRegistrationDetails.aadharNo = limitedDigits.toLongOrNull()
                                }, placeholder = {
                                    Text(text = "XXXX XXXX XXXX XXXX")
                                }, keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
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
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "DOB", fontWeight = FontWeight.Medium)
                            TextField(
                                value = tempRegistrationDetails.aadharDOB?.toString()?:"",
                                onValueChange = {
                                    val digitsOnly =
                                        it.filter { char -> char.isDigit() }  //filters only numerical digits
                                    val limitedDigits =
                                        if (digitsOnly.length > 8) {        //limited to 10 digits
                                            digitsOnly.substring(0, 8)
                                        } else {
                                            digitsOnly
                                        }
                                    tempRegistrationDetails.aadharDOB = limitedDigits.toLongOrNull()
                                }, placeholder = {
                                    Text(text = "DD / MM / YYYY")
                                }, keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
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
                    }
                    Spacer(Modifier.height(24.dp))
                    //upload photo
                    Text(
                        text = "Upload Photo of your Aadhaar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF333333)
                    )

                    Spacer(Modifier.height(16.dp))

                    //upload aadhar photo card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(200.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0xFFEB4335).copy(alpha = 0.3f),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFAFAFA)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            if (tempRegistrationDetails.aadharPhotoUri == null) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Upload Photo",
                                    modifier = Modifier
                                        .size(48.dp)
                                        .padding(bottom = 8.dp),
                                    tint = Color(0xFFEB4335)
                                )

                                imagePicker()

                                Text(
                                    text = "Click to upload your Aadhaar photo",
                                    fontSize = 14.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            } else {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    ProcessImage(
                                        LocalContext.current,
                                        tempRegistrationDetails.aadharPhotoUri!!
                                    )

                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = photoUploadStatus,
                                            color = if (photoUploadStatus == "Uploaded Successfully")
                                                Color(0xFF4CAF50) else Color(0xFFEB4335),
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 14.sp
                                        )

                                        if (photoUploadStatus == "Uploaded Successfully") {
                                            Icon(
                                                imageVector = Icons.Default.CheckCircle,
                                                contentDescription = "Success",
                                                tint = Color(0xFF4CAF50),
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .padding(top = 4.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    //save button
                    Button(
                        onClick = {
                            if (tempRegistrationDetails.aadharNo != null && tempRegistrationDetails.aadharDOB != null && checkCorrectDateStringEntered(tempRegistrationDetails.aadharDOB.toString())){
                                if (photoUploadStatus=="Uploaded Successfully"){
                                    registrationViewModel.saveAadharData()
                                    registrationViewModel.saveAadharStatus("submitted")
                                    registrationViewModel.saveRegistrationType("registered", goto_homepage)
                                }else{
                                    errorMessage = "Error: Photo is not uploaded"
                                }
                            }else{
                                errorMessage = "Error: Required entries are empty"
                            }
                        },
                        modifier = Modifier.fillMaxWidth(0.5f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEB4335))
                    ) {
                        Text(text = "Save", fontSize = 16.sp)
                    }
                    Spacer(Modifier.height(8.dp))
                    //skip for now button
                    Button(
                        onClick = {
                            //tod
                            registrationViewModel.saveRegistrationType("registered", goto_homepage)
                        },
                        modifier = Modifier.fillMaxWidth(0.5f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEB4335))
                    ) {
                        Text(text = "Skip for now", fontSize = 16.sp)
                    }
                }
            }
        }

    }
}

