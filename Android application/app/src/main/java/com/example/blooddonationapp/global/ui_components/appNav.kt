package com.example.blooddonationapp.global.ui_components

import android.app.Activity
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.blooddonationapp.auth.data.EmailLoginViewModel
import com.example.blooddonationapp.auth.data.googleAuthClient
import com.example.blooddonationapp.auth.data.GoogleAuthViewModel
import com.example.blooddonationapp.home.interfaces.homepage
import com.example.blooddonationapp.auth.interfaces.loadingpage
import com.example.blooddonationapp.auth.interfaces.loginpage
import com.example.blooddonationapp.auth.interfaces.signuppage
import com.example.blooddonationapp.global.data.currentPage
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.home.data.homeViewmodel
import com.example.blooddonationapp.home.interfaces.bloodRequests
import com.example.blooddonationapp.home.interfaces.notificationsPage
import com.example.blooddonationapp.home.interfaces.userProfile
import com.example.blooddonationapp.registration.interfaces.ageVerification
import com.example.blooddonationapp.registration.interfaces.bloodGroup
import com.example.blooddonationapp.registration.interfaces.donorDetails
import com.example.blooddonationapp.registration.interfaces.verifyAadhar
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun appNav(navController: NavHostController, googleAuthClient: googleAuthClient){
    val navController = navController
    val coroutineScope = rememberCoroutineScope()
    val viewmodel: EmailLoginViewModel = viewModel() //this is required, do not remove ("init" is called in vm)
    // I am not removing the above line right now but we need to discuss on it.

    NavHost(
        navController = navController,
        startDestination = "loadingpage") {

        composable("loadingpage"){
            currentPage = "loadingpage"
            loadingpage(
                goto_homepage = {navController.navigate("homepage")},
                goto_loginpage = {navController.navigate("loginpage")},
                goto_ageverification = {navController.navigate("ageverification")}
            )
        }
        composable("loginpage"){
            currentPage = "loginpage"

            val viewModel = viewModel<GoogleAuthViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            //checks if signin successful, then goes to loading page to check registration
            LaunchedEffect(state.isSignInSuccessful) {
                viewmodel.saveGoogleCredential()
                if (state.isSignInSuccessful) {
                    currentUser.isSearching = true
                    navController.navigate("loadingpage") {
//                        popUpTo("loginpage") { inclusive = true }
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
                }
            }

            loginpage(
                goto_homepage = {navController.navigate("homepage"){
                    popUpTo("loginpage") { inclusive = true }
                } },
                goto_signuppage = {navController.navigate("signuppage")},
                goto_loadingpage = {navController.navigate("loadingpage")},
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
            currentPage = "homepage"
            homepage(
                goto_loadingpage = {navController.navigate("loadingpage")}
            )
        }
        composable("signuppage"){
            currentPage = "signuppage"
            signuppage(
                goto_homepage = {navController.navigate("homepage")},
                goto_loginpage = {navController.navigate("loginpage")},
                goto_loadingpage = {navController.navigate("loadingpage")})
        }
        composable("ageverification"){
            currentPage = "ageverification"
            ageVerification(
                goto_donordetails = {navController.navigate("donordetails")})
        }
        composable("donordetails"){
            currentPage = "donordetails"
            donorDetails(
                goto_bloodgroup = {navController.navigate("bloodgroup")})
        }
        composable("bloodgroup"){
            currentPage = "bloodgroup"
            bloodGroup(
                goto_verifyadhaar = {navController.navigate("verifyaadhar")})
        }
        composable("verifyaadhar"){
            currentPage = "verifyaadhar"
            verifyAadhar(
                goto_homepage = {navController.navigate("homepage")}
            )
        }
        composable("bloodrequests"){
            currentPage="bloodrequests"
            bloodRequests({navController.navigate("homepage")})
        }
        composable("userprofile"){
            currentPage="userprofile"
            userProfile()
        }
        composable("notificationspage"){
            currentPage="notificationspage"
            notificationsPage(
                {navController.navigate("homepage")}
            )
        }

    }
}