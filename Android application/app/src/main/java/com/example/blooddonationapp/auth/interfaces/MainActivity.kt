package com.example.blooddonationapp.auth.interfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.blooddonationapp.auth.data.googleAuthClient
import com.example.blooddonationapp.global.ui_components.appNav
import com.example.blooddonationapp.global.ui_components.errorAlert
import com.example.blooddonationapp.ui.theme.BloodDonationAppTheme
import com.google.android.gms.auth.api.identity.Identity

class MainActivity : ComponentActivity() {

    //for google auth
    private val googleAuthUiClient by lazy {
        googleAuthClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BloodDonationAppTheme {
                val navCtrl = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    errorAlert()
                    appNav(navCtrl, googleAuthUiClient)
                }
            }
        }
    }
}