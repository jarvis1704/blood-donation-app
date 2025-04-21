package com.example.blooddonationapp.AdminEntry

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.registration.ui_components.dateYearSelector
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewAnnouncement(
    goto_activeAnnouncements:()-> Unit,
    adminViewmodel: AdminViewmodel = hiltViewModel()
){
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header section with red background
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
                        "New Announcement",
                        fontSize = 32.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Create Donation Event Announcement",
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
                    .weight(0.8f)
                    .background(Color.White)
            ) {
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth(1f)
//                        .align(Alignment.Center)
//                        .verticalScroll(rememberScrollState()),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
//                    colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black)
//                ) {
//
//                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        "Announcement Details",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFEB4335)
                    )

                    Spacer(Modifier.height(8.dp))
                    Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                    Spacer(Modifier.height(16.dp))

                    Text("Title", fontWeight = FontWeight.Medium)
                    TextField(
                        value = newAnnouncement.title,
                        onValueChange = {
                            newAnnouncement.title = it
                        },
                        placeholder = { Text("Event Title") },
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
                    Text("Location", fontWeight = FontWeight.Medium)
                    TextField(
                        value = newAnnouncement.location,
                        onValueChange = {
                            newAnnouncement.location = it
                        },
                        placeholder = { Text("Event Location") },
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
                    Text("Event Date", fontWeight = FontWeight.Medium)
                    var tempDateState = remember { mutableStateOf(newAnnouncement.date) }
                    LaunchedEffect(tempDateState.value) {
                        newAnnouncement.date = tempDateState.value
                    }
                    Spacer(Modifier.height(8.dp))
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        colors = CardDefaults.cardColors(
//                            containerColor = Color(0xFFF5F5F5),
//                            contentColor = Color.Black
//                        ),
//                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
//                    ) {
//
//                    }
                    Box(modifier = Modifier.fillMaxWidth()) {
                        dateYearSelector(
                            dateToBeUpdated = tempDateState,
                            selectedDate = LocalDate.now()
                        )
                    }

                    Spacer(Modifier.height(16.dp))
                    Text("Event Time", fontWeight = FontWeight.Medium)
                    var tempTime = remember { mutableStateOf(newAnnouncement.time) }
                    LaunchedEffect(tempTime.value) {
                        newAnnouncement.time = tempTime.value
                    }
                    TimeSelector(toUpdate = tempTime)

                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF5F5F5),
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        newAnnouncement.time?.let {
                            Text(
                                it.format(DateTimeFormatter.ofPattern("hh : mm a")),
                                modifier = Modifier.padding(vertical = 4.dp),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Spacer(Modifier.height(24.dp))
                    Button(
                        onClick = {
                            if (newAnnouncement.title.isNotEmpty() &&
                                newAnnouncement.location.isNotEmpty()){

                                adminViewmodel.newAnnouncement(goto_activeAnnouncements)
                            }else{
                                errorMessage = "Required fields are empty"
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEB4335)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            "Create Announcement",
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 4.dp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}