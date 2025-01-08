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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.auth.data.tempUserObj
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
            Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().weight(0.5f).background(Color(0xFFEB4335))
            ){
                Column(
                    modifier = Modifier.fillMaxWidth().statusBarsPadding().padding(vertical = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Donor Details", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Please Enter your Personal Details", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
                }
            }
            Box(modifier = Modifier.fillMaxWidth().weight(0.5f).background(Color.White))
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            //card
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(horizontal = 16.dp, vertical = 16.dp).align(Alignment.CenterHorizontally),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 16.dp)
                ) {

                    Text(text = "Full Name", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                    TextField(
                        value = tempRegistrationDetails.username,
                        onValueChange = { tempRegistrationDetails.username = it},
                        placeholder = { Text("Mr XYZ") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFF5F5F5),
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color(0xFFEB4335),
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            disabledPlaceholderColor = Color.LightGray,
                        ),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Gender", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        AnimatedGenderButton(
                            gender = "M",
                            isSelected = tempRegistrationDetails.gender == "M",
                            onClick = {
                                tempRegistrationDetails.gender="M"
                            },
                        )
                        AnimatedGenderButton(
                            gender = "F",
                            isSelected = tempRegistrationDetails.gender == "F",
                            onClick = {
                                tempRegistrationDetails.gender="F"
                            },
                        )
                        AnimatedGenderButton(
                            gender = "Others",
                            isSelected = tempRegistrationDetails.gender == "Others",
                            onClick = {
                                tempRegistrationDetails.gender="Others"
                            },
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Area", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                    TextField(
                        value = tempRegistrationDetails.area,
                        onValueChange = {
                            tempRegistrationDetails.area = it
                        }, placeholder = {
                            Text(text = "Enter area")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFF5F5F5),
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color(0xFFEB4335),
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            disabledPlaceholderColor = Color.LightGray

                        ),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Phone", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        //if not necessary please remove it
//                        TextField(
//                            modifier = Modifier.fillMaxWidth(0.15f),
//                            value = "+91",
//                            onValueChange = {
//
//                            },
//                            colors = TextFieldDefaults.textFieldColors(
//                                containerColor = Color(0xFFF5F5F5),
//                                unfocusedIndicatorColor = Color.Transparent,
//                                focusedIndicatorColor = Color(0xFFEB4335),
//                                focusedTextColor = Color.Black,
//                                unfocusedTextColor = Color.Black,
//                                disabledPlaceholderColor = Color.LightGray
//
//                            ),
//                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
//                            )
                        Text("+91", Modifier.padding(horizontal = 8.dp), fontWeight = FontWeight.Medium)
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
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color(0xFFF5F5F5),
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color(0xFFEB4335),
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                disabledPlaceholderColor = Color.LightGray

                            ),
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Last Donation Date, if any", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                    TextField(
                        value = "",
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
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFF5F5F5),
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color(0xFFEB4335),
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            disabledPlaceholderColor = Color.LightGray

                        ),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                    )
                }
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
                    //error is handled automatically in func
                }
                else{
                    if (tempRegistrationDetails.lastDonationDate != ""){
                        CoroutineScope(Dispatchers.IO).launch {
                            viewmodel.saveLastDonationDate(stringToTimestamp(tempRegistrationDetails.lastDonationDate))
                        }
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
            },
                modifier = Modifier.fillMaxWidth(0.6f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEB4335))) {
                Text(text = "Next", color = Color.White, modifier = Modifier.padding(vertical = 4.dp), fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun AnimatedGenderButton(
    gender: String,
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
            text = gender,
            color = textColor
        )
    }
}
