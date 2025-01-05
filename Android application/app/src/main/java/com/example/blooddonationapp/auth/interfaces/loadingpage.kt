package com.example.blooddonationapp.auth.interfaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.blooddonationapp.global.data.currentUser
import kotlinx.coroutines.delay

@Composable
fun loadingpage(goto_loginpage:()->Unit, goto_homepage:()->Unit){
    /*this page will check whether the user is logged in or not
    * and based on that, we will navigate to homepage or loginpage */

    //checks if current user logged in
    LaunchedEffect(key1 = Unit) {
        while (currentUser.isSearching){
            delay(200)
        }
        if (currentUser.isLoggedIn){
            delay(1000)
            goto_homepage()
        }else{
            delay(1000)
            goto_loginpage()
        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Red Cross Society")
            Text(text = "Tezpur")
        }
    }
}