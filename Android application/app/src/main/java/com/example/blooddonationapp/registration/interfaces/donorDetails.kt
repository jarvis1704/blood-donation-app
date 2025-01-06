package com.example.blooddonationapp.registration.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.blooddonationapp.registration.data.tempRegistrationDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun donorDetails(goto_bloodgroup:()->Unit){
    Box(modifier = Modifier.fillMaxSize()){

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Donor Details")

            //card
            Card(onClick = { /*TODO*/ }) {
                Text(text = "Full Name")
                TextField(
                    value = tempRegistrationDetails.username,
                    onValueChange = {
                        tempRegistrationDetails.username = it
                    }, placeholder = {
                        Text(text = "Username")
                    })
                Text(text = "Gender")
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (tempRegistrationDetails.gender=="M") Color.Red else Color.White
                        ),
                        onClick = {
                        tempRegistrationDetails.gender="M"
                    }) {
                        Text(text = "M", color = if (tempRegistrationDetails.gender=="M") Color.White else Color.Black)
                    }
                    Button(modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (tempRegistrationDetails.gender=="F") Color.Red else Color.White
                        ),
                        onClick = {
                            tempRegistrationDetails.gender="F"
                        }) {
                        Text(text = "F", color = if (tempRegistrationDetails.gender=="F") Color.White else Color.Black)
                    }
                    Button(modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (tempRegistrationDetails.gender=="Others") Color.Red else Color.White
                        ),
                        onClick = {
                            tempRegistrationDetails.gender="Others"
                        }) {
                        Text(text = "Others", color = if (tempRegistrationDetails.gender=="Others") Color.White else Color.Black)
                    }
                }
                Text(text = "Area")
                TextField(
                    value = tempRegistrationDetails.area,
                    onValueChange = {
                        tempRegistrationDetails.area = it
                    }, placeholder = {
                        Text(text = "Enter area")
                    })
                Text(text = "Phone")
                TextField(
                    value = tempRegistrationDetails.phoneNo.toString(),
                    onValueChange = {
                        tempRegistrationDetails.phoneNo = it.toInt()
                    }, placeholder = {
                        Text(text = "Username")
                    }, keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                Text(text = "Last Donation Date, if any")
                TextField(
                    value = tempRegistrationDetails.lastDonationDate,
                    onValueChange = {
                        tempRegistrationDetails.lastDonationDate = it
                    }, placeholder = {
                        Text(text = "Username")
                    }, keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
            }
            //location from google maps
            Text(text = "Location")
            //todo location
            
            //next button
            Button(onClick = {
                goto_bloodgroup()
            }) {
                Text(text = "Next")
            }
        }
    }
}