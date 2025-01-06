package com.example.blooddonationapp.registration.interfaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.blooddonationapp.registration.data.tempRegistrationDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bloodGroup(goto_verifyadhaar:()->Unit){
    Box(modifier = Modifier.fillMaxSize()){

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Blood Group")
            Text(text = "Please select your blood group")
            //card
            Card(modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ })
            {
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center)
                {
                    Text(text = "Blood Group")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (tempRegistrationDetails.bloodGroup == "A+") Color.Red else Color.White
                            ),
                            onClick = {
                                tempRegistrationDetails.bloodGroup = "A+"
                            }) {
                            Text(
                                text = "A+",
                                color = if (tempRegistrationDetails.bloodGroup == "A+") Color.White else Color.Black
                            )
                        }
                        Button(modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (tempRegistrationDetails.bloodGroup == "B+") Color.Red else Color.White
                            ),
                            onClick = {
                                tempRegistrationDetails.bloodGroup = "B+"
                            }) {
                            Text(
                                text = "B+",
                                color = if (tempRegistrationDetails.bloodGroup == "B+") Color.White else Color.Black
                            )
                        }
                        Button(modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (tempRegistrationDetails.bloodGroup == "AB+") Color.Red else Color.White
                            ),
                            onClick = {
                                tempRegistrationDetails.bloodGroup = "AB+"
                            }) {
                            Text(
                                text = "AB+",
                                color = if (tempRegistrationDetails.bloodGroup == "AB+") Color.White else Color.Black
                            )
                        }
                        Button(modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (tempRegistrationDetails.bloodGroup == "O+") Color.Red else Color.White
                            ),
                            onClick = {
                                tempRegistrationDetails.bloodGroup = "O+"
                            }) {
                            Text(
                                text = "O+",
                                color = if (tempRegistrationDetails.bloodGroup == "O+") Color.White else Color.Black
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (tempRegistrationDetails.bloodGroup == "A-") Color.Red else Color.White
                            ),
                            onClick = {
                                tempRegistrationDetails.bloodGroup = "A-"
                            }) {
                            Text(
                                text = "A-",
                                color = if (tempRegistrationDetails.bloodGroup == "A-") Color.White else Color.Black
                            )
                        }
                        Button(modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (tempRegistrationDetails.bloodGroup == "B-") Color.Red else Color.White
                            ),
                            onClick = {
                                tempRegistrationDetails.bloodGroup = "B-"
                            }) {
                            Text(
                                text = "B-",
                                color = if (tempRegistrationDetails.bloodGroup == "B-") Color.White else Color.Black
                            )
                        }
                        Button(modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (tempRegistrationDetails.bloodGroup == "AB-") Color.Red else Color.White
                            ),
                            onClick = {
                                tempRegistrationDetails.bloodGroup = "AB-"
                            }) {
                            Text(
                                text = "AB-",
                                color = if (tempRegistrationDetails.bloodGroup == "AB-") Color.White else Color.Black
                            )
                        }
                        Button(modifier = Modifier,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (tempRegistrationDetails.bloodGroup == "O-") Color.Red else Color.White
                            ),
                            onClick = {
                                tempRegistrationDetails.bloodGroup = "O-"
                            }) {
                            Text(
                                text = "O-",
                                color = if (tempRegistrationDetails.bloodGroup == "O-") Color.White else Color.Black
                            )
                        }
                    }
                }
            }

            //next button
            Button(onClick = {
                goto_verifyadhaar()
            }) {
                Text(text = "Next")
            }
        }
    }
}