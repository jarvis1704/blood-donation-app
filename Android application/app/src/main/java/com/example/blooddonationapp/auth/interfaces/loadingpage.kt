package com.example.blooddonationapp.auth.interfaces

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.blooddonationapp.R
import com.example.blooddonationapp.global.data.currentUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun loadingpage(goto_loginpage:()->Unit, goto_homepage:()->Unit) {
    /*this page will check whether the user is logged in or not
    * and based on that, we will navigate to homepage or loginpage */

    //checks if current user logged in
//    LaunchedEffect(key1 = Unit) {
//        while (currentUser.isSearching){
//            delay(200)
//        }
//        if (currentUser.isLoggedIn){
//            delay(1000)
//            goto_homepage()
//        }else{
//            delay(1000)
//            goto_loginpage()
//        }
//    }
    LaunchedEffect(key1 = Unit) {
        // Move to a background dispatcher for the polling
        withContext(Dispatchers.Default) {
            while (currentUser.isSearching) {
                delay(200)
            }
        }
        // After polling is done, handle navigation
        if (currentUser.isLoggedIn) {
            delay(1000)
            goto_homepage()
        } else {
            delay(1000)
            goto_loginpage()
        }
    }


    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.red_cross_logo), contentDescription = "Red Cross Logo", modifier = Modifier.height(120.dp).width(120.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Red Cross Society", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Tezpur", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.SemiBold, color = Color.Black)
        }
    }
}

@Preview
@Composable
private fun LoadingPagePreview() {
    loadingpage({}) { }
}