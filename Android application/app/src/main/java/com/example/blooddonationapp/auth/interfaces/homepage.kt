package com.example.blooddonationapp.auth.interfaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.auth.data.emailLoginViewmodel

@Composable
fun homepage(goto_loadingpage:()->Unit){
    var viewmodel: emailLoginViewmodel = viewModel()
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "this is homepage\n\n\nyou are logged in")
            Button(onClick = {
                viewmodel.signout(goto_loadingpage)
            }) {
                Text(text = "logout")
            }
        }
    }
}