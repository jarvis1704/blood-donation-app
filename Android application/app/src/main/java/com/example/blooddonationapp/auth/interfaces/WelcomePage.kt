package com.example.blooddonationapp.auth.interfaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun WelcomePage(
    goto_bloodreqform:()->Unit,
    goto_loginpage:()->Unit,
    goto_adminloginpage:()->Unit
){
    Box(
        modifier = Modifier.fillMaxSize(),
    ){
        Column (
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text("Welcome to Blood Donation App")
            Button(onClick = {
                goto_bloodreqform()
            } ) {
                Text("Need Emergency Blood? Click here!")
            }
            Text("Or")
            Button(onClick = {
                goto_loginpage()
            } ) {
                Text("Login/Signup")
            }
            TextButton(onClick = {
                goto_adminloginpage()
            }) {
                Text("Admin? Click Here")
            }
        }
    }
}