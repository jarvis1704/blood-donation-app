package com.example.blooddonationapp.auth.interfaces

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.blooddonationapp.auth.data.googleAuthClient
import com.example.blooddonationapp.global.ui_components.appNav
import com.example.blooddonationapp.global.ui_components.errorAlert
import com.example.blooddonationapp.home.interfaces.homepage
import com.example.blooddonationapp.home.ui_components.bottomBar
import com.example.blooddonationapp.home.ui_components.notifButton
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
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BloodDonationAppTheme {
                val navCtrl = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { bottomBar(
                        goto_homepage = {navCtrl.navigate("homepage")},
                        goto_bloodrequests = {navCtrl.navigate("bloodrequests")},
                        goto_userprofile = {navCtrl.navigate("userprofile")}
                    )}) { innerPadding ->
                    errorAlert()
                    appNav(navCtrl, googleAuthUiClient)
                }
            }
        }
    }
}