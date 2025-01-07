package com.example.blooddonationapp.registration.interfaces

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.registration.data.registrationViewmodel
import com.example.blooddonationapp.registration.data.tempRegistrationDetails

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun verifyAadhar(goto_homepage:()->Unit){
    var registrationViewmodel: registrationViewmodel = viewModel()

    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Verify Aadhar")
            //card
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ }
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Aadhar Number")
                    TextField(
                        value = tempRegistrationDetails.aadharNo?.toString()?:"",
                        onValueChange = {
                            tempRegistrationDetails.aadharNo = it.toLongOrNull()
                        }, placeholder = {
                            Text(text = "XXXX XXXX XXXX XXXX")
                        }, keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                    Text(text = "DOB")
                    TextField(
                        value = tempRegistrationDetails.aadharDOB,
                        onValueChange = {
                            tempRegistrationDetails.aadharDOB = it
                        }, placeholder = {
                            Text(text = "DD / MM / YYYY")
                        }, keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            }

            //upload photo
            Text(text = "Upload Photo of your Aadhar")
            //todo

            //save button
            Button(onClick = {
                //todo
                registrationViewmodel.saveRegistrationType("registered", goto_homepage)
            }) {
                Text(text = "Save")
            }
            //skip for now button
            Button(onClick = {
                //todo
                registrationViewmodel.saveRegistrationType("registered", goto_homepage)
            }) {
                Text(text = "Skip for now")
            }
        }
    }
}