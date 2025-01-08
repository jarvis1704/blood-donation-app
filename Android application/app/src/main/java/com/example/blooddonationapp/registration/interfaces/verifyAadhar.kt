package com.example.blooddonationapp.registration.interfaces

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.registration.data.registrationViewmodel
import com.example.blooddonationapp.registration.data.tempRegistrationDetails

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun verifyAadhar(goto_homepage:()->Unit){
    var registrationViewmodel: registrationViewmodel = viewModel()

    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().weight(0.5f).background(Color(0xFFEB4335))
            ){
                Column(
                    modifier = Modifier.fillMaxWidth().statusBarsPadding().padding(vertical = 80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Verify Aadhar", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Please Enter your Aadhar Details for verification", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
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
                    .fillMaxWidth(0.9f)
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black)

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
                            tempRegistrationDetails.aadharNo = it.toLongOrNull()
                        }, placeholder = {
                            Text(text = "XXXX XXXX XXXX XXXX")
                        }, keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFF5F5F5),
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
                        value = tempRegistrationDetails.aadharDOB,
                        onValueChange = {
                            tempRegistrationDetails.aadharDOB = it
                        }, placeholder = {
                            Text(text = "DD / MM / YYYY")
                        }, keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFF5F5F5),
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
            Spacer(Modifier.height(16.dp))
            //upload photo
            Text(text = "Upload Photo of your Aadhaar", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            //todo

            Spacer(Modifier.height(60.dp))
            //save button
            Button(onClick = {
                //todo
                registrationViewmodel.saveRegistrationType("registered", goto_homepage)
            },
                modifier = Modifier.fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEB4335))
            ) {
                Text(text = "Save", fontSize = 16.sp)
            }
            Spacer(Modifier.height(8.dp))
            //skip for now button
            Button(onClick = {
                //tod
                registrationViewmodel.saveRegistrationType("registered", goto_homepage)
            },
                modifier = Modifier.fillMaxWidth(0.5f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEB4335))
            ) {
                Text(text = "Skip for now", fontSize = 16.sp)
            }
        }
    }
}
