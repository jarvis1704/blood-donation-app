package com.example.blooddonationapp.auth.interfaces

import android.content.Context
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.R
import com.example.blooddonationapp.auth.data.EmailLoginViewModel
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.registration.data.RegistrationViewModel
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import kotlinx.coroutines.delay

@Composable
fun loadingpage(
    goto_welcomepage: () -> Unit,
    goto_homepage: () -> Unit,
    emailLoginViewModel: EmailLoginViewModel = hiltViewModel(),
    registrationViewmodel: RegistrationViewModel = hiltViewModel()
){
    /*this page will check whether the user is logged in or not
    * and based on that, we will navigate to homepage or loginpage */

//    var emailLoginViewmodel:EmailLoginViewModel = viewModel()
//    var registrationViewmodel: RegistrationViewModel = viewModel()

    subscribeToGlobalTopic(context = LocalContext.current)

    LaunchedEffect(currentUser.isSearching) {
        while (currentUser.isSearching){
            emailLoginViewModel.checkLoginStatus()
            delay(200)
        }
        if (currentUser.isLoggedIn){
            currentUser.registrationType = registrationViewmodel.getRegistrationType()
//            Log.d("checkLogin", "returned from getRegis, registrationtype="+currentUser.registrationType)
            delay(300)
            goto_homepage()
//            if (currentUser.registrationType == "registered"){
//                delay(300)
//                goto_homepage()
//            }else{
//                goto_ageverification()
//            }
        }else{
            delay(1000)
            goto_welcomepage()
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

fun subscribeToGlobalTopic(context: Context) {
    val sharedPrefs = context.getSharedPreferences("FCMPrefs", Context.MODE_PRIVATE)
    val isSubscribed = sharedPrefs.getBoolean("isSubscribedToGlobal", false)

    if (!isSubscribed) {
        Firebase.messaging.subscribeToTopic("global")
            .addOnCompleteListener { task ->
                val subscriptionStatusMessage = when {
                    task.isSuccessful -> {
                        sharedPrefs.edit().putBoolean("isSubscribedToGlobal", true).apply()
                        context.getString(R.string.subscribed)
                    }
                    else -> {
                        Log.e("firebase", "Subscribe failed", task.exception)
                        context.getString(R.string.subscribe_failed)
                    }
                }
                Log.d("firebase", subscriptionStatusMessage)
                // Toast.makeText(context, subscriptionStatusMessage, Toast.LENGTH_SHORT).show()
            }
    } else {
        Log.d("firebase", "Already subscribed to global topic")
    }
}
//
//@Preview
//@Composable
//private fun LoadingPagePreview() {
//    loadingpage({}) { }
//}