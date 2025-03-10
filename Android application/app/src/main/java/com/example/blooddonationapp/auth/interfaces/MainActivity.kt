package com.example.blooddonationapp.auth.interfaces

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.blooddonationapp.auth.data.googleAuthClient
import com.example.blooddonationapp.global.ui_components.appNav
import com.example.blooddonationapp.global.ui_components.errorAlert
import com.example.blooddonationapp.global.ui_components.globalAlert
import com.example.blooddonationapp.home.ui_components.bottomBar
import com.example.blooddonationapp.home.ui_components.notifButton
import com.example.blooddonationapp.ui.theme.BloodDonationAppTheme
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    //for google auth
    private val googleAuthUiClient by lazy {
        googleAuthClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BloodDonationAppTheme {
                val navCtrl = rememberNavController()
                Scaffold(
                    floatingActionButton = { notifButton(
                        goto_notifications = {navCtrl.navigate("notificationspage")}
                    )},
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { bottomBar(
                        goto_homepage = {navCtrl.navigate("homepage")},
                        goto_bloodrequests = {navCtrl.navigate("bloodrequests")},
                        goto_userprofile = {navCtrl.navigate("userprofile")}
                    )}) { _ ->
                    askNotificationPermission()
                    errorAlert()
                    globalAlert()
                    appNav(navCtrl, googleAuthUiClient)
                }
            }
        }
    }
}