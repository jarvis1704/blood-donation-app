package com.example.blooddonationapp.auth.interfaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AdminLoginPage(
    goto_adminpannel:()->Unit
){
    var Passkey = remember { mutableStateOf("") }
    Column (
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text("Admin Login")
        TextField(
            value = Passkey.value,
            onValueChange = {
                Passkey.value = it
            },
            placeholder = { Text("Enter Passkey") }
        )
        Button(
            onClick = {
                goto_adminpannel()
            }
        ) {
            Text("Continue")
        }
    }
}