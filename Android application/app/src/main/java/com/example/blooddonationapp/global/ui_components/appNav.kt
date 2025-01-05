package com.example.blooddonationapp.global.ui_components

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.blooddonationapp.auth.data.emailLoginViewmodel
import com.example.blooddonationapp.auth.data.googleAuthClient
import com.example.blooddonationapp.auth.data.googleAuthViewmodel
import com.example.blooddonationapp.auth.interfaces.homepage
import com.example.blooddonationapp.auth.interfaces.loadingpage
import com.example.blooddonationapp.auth.interfaces.loginpage
import com.example.blooddonationapp.global.data.errorMessage
import kotlinx.coroutines.launch


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
                            errorMessage = e.message.toString()
                        }
                    }
                }else{
                    errorMessage = "appNav.launcher: Result code not OK"
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
                            errorMessage = e.message.toString()
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