package com.example.blooddonationapp.registration.interfaces

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.registration.data.RegistrationViewModel
import com.example.blooddonationapp.registration.data.tempRegistrationDetails

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun bloodGroup(
    goto_verifyadhaar: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel()
) {

//    var viewModel: RegistrationViewModel = viewModel()

    //variable for each blood group
    val positiveBloodGroups = listOf("A+", "B+", "AB+", "O+")
    val negativeBloodGroups = listOf("A-", "B-", "AB-", "O-")

    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .background(Color(0xFFEB4335))

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(top = 100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Blood Group",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = "Please select your blood group",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .background(Color.White)
            ) {

            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            //Blood Group Selector
            Card(
                modifier = Modifier.fillMaxWidth(0.95f),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                )
                {
                    Text(
                        text = "Blood Group",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
//                      Added loop with list for repeated buttons
                        positiveBloodGroups.forEach { bloodType ->
                            AnimatedBloodGroupButton(
                                bloodType = bloodType,
                                isSelected = tempRegistrationDetails.bloodGroup == bloodType,
                                onClick = { tempRegistrationDetails.bloodGroup = bloodType }
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
//                        Similarly here
                        negativeBloodGroups.forEach { bloodgroup ->
                            AnimatedBloodGroupButton(
                                bloodType = bloodgroup,
                                isSelected = tempRegistrationDetails.bloodGroup == bloodgroup,
                                onClick = { tempRegistrationDetails.bloodGroup = bloodgroup }
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(32.dp))
            //next button
            Button(
                onClick = {
                    if (tempRegistrationDetails.bloodGroup != "") {
                        viewModel.saveRegistrationEntryByString(
                            "bloodGroup",
                            tempRegistrationDetails.bloodGroup,
                            goto_verifyadhaar
                        )
                    }
                },
                modifier = Modifier
                    .width(160.dp)
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEB4335)
                )
            ) {
                Text(text = "Next", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}


@Composable
fun AnimatedBloodGroupButton(
    bloodType: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFFEB4335) else Color(0xFFf8dede),
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else Color.Black,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )

    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        onClick = onClick
    ) {
        Text(
            text = bloodType,
            color = textColor
        )
    }
}


//Original code to recover if anything goes wrong
// Button(modifier = Modifier,
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = if (tempRegistrationDetails.bloodGroup == "A+") Color(0xFFEB4335) else Color(0xFFf8dede),
//                            ),
//                            onClick = {
//                                tempRegistrationDetails.bloodGroup = "A+"
//                            }) {
//                            Text(
//                                text = "A+",
//                                color = if (tempRegistrationDetails.bloodGroup == "A+") Color.White else Color.Black
//                            )
//                        }
//                        Button(modifier = Modifier,
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = if (tempRegistrationDetails.bloodGroup == "B+") Color(0xFFEB4335) else Color(0xFFf8dede)
//                            ),
//                            onClick = {
//                                tempRegistrationDetails.bloodGroup = "B+"
//                            }) {
//                            Text(
//                                text = "B+",
//                                color = if (tempRegistrationDetails.bloodGroup == "B+") Color.White else Color.Black
//                            )
//                        }
//                        Button(modifier = Modifier,
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = if (tempRegistrationDetails.bloodGroup == "AB+") Color(0xFFEB4335) else Color(0xFFf8dede)
//                            ),
//                            onClick = {
//                                tempRegistrationDetails.bloodGroup = "AB+"
//                            }) {
//                            Text(
//                                text = "AB+",
//                                color = if (tempRegistrationDetails.bloodGroup == "AB+") Color.White else Color.Black
//                            )
//                        }
//                        Button(modifier = Modifier,
//                            colors = ButtonDefaults.buttonColors(
//                                containerColor = if (tempRegistrationDetails.bloodGroup == "O+") Color(0xFFEB4335) else Color(0xFFf8dede)
//                            ),
//                            onClick = {
//                                tempRegistrationDetails.bloodGroup = "O+"
//                            }) {
//                            Text(
//                                text = "O+",
//                                color = if (tempRegistrationDetails.bloodGroup == "O+") Color.White else Color.Black
//                            )
//                        }