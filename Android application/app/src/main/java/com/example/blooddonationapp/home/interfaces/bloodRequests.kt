package com.example.blooddonationapp.home.interfaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.auth.data.emailLoginViewmodel
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.global.data.updateCurrentUser

@Composable
fun bloodRequests(){
    updateCurrentUser()
    Box(modifier = Modifier.fillMaxSize().padding(50.dp)){
        Column(
            modifier = Modifier.fillMaxSize().padding(top = 100.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(text = "BLOOD REQUESTS")
            Text(currentUser.bloodGroup+" Type Blood Required Near You")
            Spacer(modifier = Modifier.padding(100.dp))
            Text("All Blood Requests")
        }
    }
}