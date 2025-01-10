package com.example.blooddonationapp.home.interfaces

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.auth.data.EmailLoginViewModel
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.global.data.updateCurrentUser

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun homepage(
    goto_loadingpage: () -> Unit,
    viewModel: EmailLoginViewModel = hiltViewModel()
) {
//    var viewModel: EmailLoginViewModel = viewModel()
    updateCurrentUser()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "WELCOME")
            Text(text = currentUser.username)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    onClick = {}
                ) {
                    Text("DONATE")
                }
                Card(
                    onClick = {}
                ) {
                    Text("FIND DONOR")
                }
            }
            Text("SEE WHAT'S NEW!")
            Button(onClick = {
                viewModel.signout(goto_loadingpage)
            }) {
                Text(text = "logout")
            }
        }
    }
}

