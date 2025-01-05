package com.example.blooddonationapp.registration.interfaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.registration.data.registrationViewmodel

@Composable
fun verifyAdhaar(goto_homepage:()->Unit){
    var registrationViewmodel: registrationViewmodel = viewModel()

    Box(modifier = Modifier.fillMaxSize()){

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "this is verify adhaar page")
            Button(onClick = {
                //todo
                registrationViewmodel.saveRegistrationType("registered", goto_homepage)
            }) {
                Text(text = "Next")
            }
        }
    }
}