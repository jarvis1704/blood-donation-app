package com.example.blooddonationapp.auth.interfaces

import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.registration.data.registrationViewmodel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.blooddonationapp.R
import com.example.blooddonationapp.auth.data.emailLoginViewmodel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun loadingpage(
    goto_loginpage:()->Unit,
    goto_homepage:()->Unit,
    goto_ageverification:()->Unit){
    /*this page will check whether the user is logged in or not
    * and based on that, we will navigate to homepage or loginpage */

    var emailLoginViewmodel:emailLoginViewmodel = viewModel()
    var registrationViewmodel: registrationViewmodel = viewModel()

    LaunchedEffect(currentUser.isSearching) {
        while (currentUser.isSearching){
            emailLoginViewmodel.checkLoginStatus()
            delay(200)
        }
        if (currentUser.isLoggedIn){
            currentUser.registrationType = registrationViewmodel.getRegistrationType()
            Log.d("checkLogin", "returned from getRegis, registrationtype="+currentUser.registrationType)
            if (currentUser.registrationType == "registered"){
                delay(300)
                goto_homepage()
            }else{
                goto_ageverification()
            }
        }else{
          delay(1000)
            goto_loginpage()
        }
    }


    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.red_cross_logo), contentDescription = "Red Cross Logo", modifier = Modifier
                .height(120.dp)
                .width(120.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Red Cross Society", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Tezpur", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.SemiBold, color = Color.Black)
        }
    }
}
//
//@Preview
//@Composable
//private fun LoadingPagePreview() {
//    loadingpage({}) { }
//}