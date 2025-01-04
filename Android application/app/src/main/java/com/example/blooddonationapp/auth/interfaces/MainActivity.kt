package com.example.blooddonationapp.auth.interfaces

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.blooddonationapp.auth.data.emailLoginViewmodel
import com.example.blooddonationapp.auth.data.googleAuthClient
import com.example.blooddonationapp.auth.data.googleAuthViewmodel
import com.example.blooddonationapp.ui.theme.BloodDonationAppTheme
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

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
                    appNav(navCtrl, googleAuthUiClient)
                }
            }
        }
    }
}

@Composable
fun appNav(navController: NavHostController, googleAuthClient: googleAuthClient){
    val navController = navController
    val coroutineScope = rememberCoroutineScope()
    val viewmodel: emailLoginViewmodel = viewModel() //this is required, do not remove ("init" is called in vm)

    NavHost(
        navController = navController,
        startDestination = "loadingpage") {

        composable("loadingpage"){
            loadingpage(
                goto_homepage = {navController.navigate("homepage")},
                goto_loginpage = {navController.navigate("loginpage")}
            )
        }
        composable("loginpage"){

            val viewModel = viewModel<googleAuthViewmodel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            //checks if signin successful, then goes to homepage
            LaunchedEffect(state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    navController.navigate("homepage") {
                        popUpTo("loginpage") { inclusive = true }
                    }
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult()
            ) {result->
                if(result.resultCode == Activity.RESULT_OK){
                    coroutineScope.launch {
                        try {
                            val signInResult = googleAuthClient.getSignInResultFromIntent(
                                intent = result.data?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }catch (e:Exception){
                            //todo handle error
                        }
                    }
                }else{
                    //todo handle error
                }
            }

            loginpage(
                goto_homepage = {navController.navigate("homepage"){
                    popUpTo("loginpage") { inclusive = true }
                } },
                goto_signuppage = {navController.navigate("signuppage")},

                //for login with google
                state = state,
                onSignInClick = {
                    coroutineScope.launch{
                        try{
                            val signInIntentSender = googleAuthClient.signIn()
                            launcher.launch(
                                IntentSenderRequest.Builder(
                                    signInIntentSender ?: return@launch
                                ).build()
                            )
                        }catch (e:Exception){
                            //todo handle error
                            e.printStackTrace()
                        }
                    }
                }
            )
        }
        composable("homepage"){
            homepage(
                goto_loadingpage = {navController.navigate("loadingpage")}
            )
        }
    }
}