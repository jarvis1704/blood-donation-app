package com.example.blooddonationapp.AdminEntry.interfaces

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.AdminEntry.data.AdminViewModel
import com.example.blooddonationapp.AdminEntry.data.AppUser
import com.example.blooddonationapp.AdminEntry.data.aadharPendingList
import com.example.blooddonationapp.AdminEntry.data.aadharUser
import com.example.blooddonationapp.global.data.NewGlobalAlert
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AadharVerificationPage(
    AdminViewmodel: AdminViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        AdminViewmodel.getPendingAadhar()
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
                        "Aadhar Verification",
                        fontSize = 32.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Verify pending aadhar details",
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
                    aadharPendingList?.let {
                        items(it.toList()) { item ->
                            showAadharUser(item, AdminViewmodel)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun showAadharUser(user: aadharUser, viewModel: AdminViewModel) {
    var isDataFound = remember { mutableStateOf(false) }

    var userdetails = remember { mutableStateOf<AppUser>(AppUser("","","","","", LocalDateTime.now())) }
    viewModel.GetUserData(user.userid,isDataFound,userdetails)

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

            when(isDataFound.value){
                false->{
                    CircularProgressIndicator()
                }
                true->{

                    //visible content here

                    Text("user details:")
                    Text(userdetails.value.username)
                    Text(userdetails.value.gender)
                    Text(userdetails.value.phoneno)

                    Text(
                        "User Email",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF757575),
                        fontSize = 14.sp
                    )
                    Text(
                        user.useremail,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Aadhar Number",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF757575),
                        fontSize = 14.sp
                    )
                    Text(
                        user.aadharNo.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Date of Birth",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF757575),
                        fontSize = 14.sp
                    )
                    Text(
                        user.aadharDOB.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Status",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF757575),
                        fontSize = 14.sp
                    )
                    Text(
                        user.aadharStatus,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = when(user.aadharStatus) {
                            "Pending" -> Color(0xFFFFA000)
                            "Approved" -> Color(0xFF4CAF50)
                            "Rejected" -> Color(0xFFD32F2F)
                            else -> Color.Black
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Aadhar Card Image",
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF757575),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        DisplayImageFromBase64(user.aadharPhotoString)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Action buttons
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            Button(
                                onClick = {
                                    NewGlobalAlert(
                                        title = "Reject Aadhaar",
                                        details = "Are you sure you want to disapprove the request?\n\nThe request will be deleted and cannot be undone.",
                                        onCancelClick = {},
                                        onConfirmClick = {
                                            viewModel.SetUserAadhaarStatus(user.id,"rejected")
                                        }
                                    )
                                },
                                modifier = Modifier,
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEB4335))
                            ) {
                                Text(
                                    "Reject",
                                    color = Color.White,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Reject",
                                    Modifier.size(17.dp),
                                    tint = Color.White
                                )
                            }
                            Button(
                                onClick = {
                                    NewGlobalAlert(
                                        title = "Approve Aadhaar",
                                        details = "Are you sure you want to approve the request?\n\nThis action cannot be undone.",
                                        onCancelClick = {},
                                        onConfirmClick = {
                                            viewModel.SetUserAadhaarStatus(user.id,"verified")
                                        }
                                    )
                                },
                                modifier = Modifier,
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEB4335))
                            ) {
                                Text(
                                    "Approve",
                                    color = Color.White,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun getBitmapFromBase64(base64String: String): Bitmap? {
    return try {
        val decodedString = Base64.decode(base64String, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Composable
fun DisplayImageFromBase64(base64String: String) {
    val bitmap = getBitmapFromBase64(base64String)
    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = "Aadhar Card Image",
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}