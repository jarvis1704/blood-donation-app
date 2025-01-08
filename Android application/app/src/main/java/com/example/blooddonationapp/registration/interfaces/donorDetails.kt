package com.example.blooddonationapp.registration.interfaces

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.global.data.checkCorrectDateStringEntered
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.global.data.stringToTimestamp
import com.example.blooddonationapp.registration.data.registrationViewmodel
import com.example.blooddonationapp.registration.data.tempRegistrationDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun donorDetails(goto_bloodgroup:()->Unit){

    //todo get already existing user details here
    var viewmodel:registrationViewmodel = viewModel()
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

                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    TextField(
                        modifier = Modifier.fillMaxWidth(0.15f),
                        value = "+91",
                        onValueChange = {

                        })
                    TextField(
                        value = tempRegistrationDetails.phoneNo,
                        onValueChange = {
                            val digitsOnly = it.filter { char -> char.isDigit() }  //filters only numerical digits
                            val limitedDigits = if(digitsOnly.length > 10){        //limited to 10 digits
                                digitsOnly.substring(0, 10)
                            }else{
                                digitsOnly
                            }
                            tempRegistrationDetails.phoneNo = limitedDigits
                        }, placeholder = {
                            Text(text = "XXX XXX XXXX")
                        }, keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
                Text(text = "Last Donation Date, if any")
                TextField(
                    value = tempRegistrationDetails.lastDonationDate,
                    onValueChange = {
                        val digitsOnly = it.filter { char -> char.isDigit() }
                        val limitedDigits = if(digitsOnly.length > 8){
                            digitsOnly.substring(0,8)
                        }else{
                            digitsOnly
                        }
                        tempRegistrationDetails.lastDonationDate = limitedDigits
                    }, placeholder = {
                        Text(text = "DD MM YYYY")
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
                //todo check if all entries non empty
                if (tempRegistrationDetails.username == ""
                    || tempRegistrationDetails.gender == ""
                    || tempRegistrationDetails.area == ""
                    ){
                    errorMessage = "Error: Required entries are empty"
                }else if(tempRegistrationDetails.phoneNo.toLong() < 1000000000){
                    errorMessage = "Error: Enter a valid phone number"
                }else if(tempRegistrationDetails.phoneNo.toLong() > 9999999999){
                    errorMessage = "Error: Enter a valid phone number"
                }else if (
                    tempRegistrationDetails.lastDonationDate != "" && !checkCorrectDateStringEntered(tempRegistrationDetails.lastDonationDate)
                ){
                    //error
                }
                else{
                    if (tempRegistrationDetails.lastDonationDate != ""){
                        CoroutineScope(Dispatchers.IO).launch {
                            viewmodel.saveLastDonationDate(stringToTimestamp(tempRegistrationDetails.lastDonationDate))
                        }
                    }

                    viewmodel.saveRegistrationEntryByString("username", tempRegistrationDetails.username)
                    viewmodel.saveRegistrationEntryByString("gender", tempRegistrationDetails.gender)
                    viewmodel.saveRegistrationEntryByString("area", tempRegistrationDetails.area)
                    viewmodel.saveRegistrationEntryByString("phoneNo", tempRegistrationDetails.phoneNo, goto_bloodgroup)
                }
            }) {
                Text(text = "Next")
            }
        }
    }
}
