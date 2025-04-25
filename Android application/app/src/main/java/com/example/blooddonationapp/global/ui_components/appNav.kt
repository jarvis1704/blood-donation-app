package com.example.blooddonationapp.global.ui_components

import android.app.Activity
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import com.example.blooddonationapp.auth.interfaces.AdminLoginPage
import com.example.blooddonationapp.auth.interfaces.WelcomePage
import com.example.blooddonationapp.home.interfaces.homepage
import com.example.blooddonationapp.auth.interfaces.loadingpage
import com.example.blooddonationapp.auth.interfaces.loginpage
import com.example.blooddonationapp.auth.interfaces.signuppage
import com.example.blooddonationapp.global.data.currentPage
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.home.interfaces.BloodRequestForm
import com.example.blooddonationapp.home.interfaces.bloodRequests
import com.example.blooddonationapp.home.interfaces.NotificationsPage
import com.example.blooddonationapp.home.interfaces.userProfile
import com.example.blooddonationapp.registration.interfaces.ageVerification
import com.example.blooddonationapp.registration.interfaces.BloodGroup
import com.example.blooddonationapp.registration.interfaces.donorDetails
import com.example.blooddonationapp.registration.interfaces.verifyAadhar
import com.example.blooddonationapp.settings.interfaces.AboutUs
import com.example.blooddonationapp.settings.interfaces.SettingsAndPreferences
import com.example.blooddonationapp.AdminEntry.interfaces.AadharVerificationPage
import com.example.blooddonationapp.AdminEntry.interfaces.ActiveAnnouncements
import com.example.blooddonationapp.AdminEntry.interfaces.ActiveBloodRequests
import com.example.blooddonationapp.AdminEntry.interfaces.AdminHomepage
import com.example.blooddonationapp.AdminEntry.interfaces.AdminPasskeys
import com.example.blooddonationapp.AdminEntry.interfaces.BloodReqVerificationPage
import com.example.blooddonationapp.AdminEntry.interfaces.EmergencyNumbers
import com.example.blooddonationapp.AdminEntry.interfaces.NewAnnouncement
import com.example.blooddonationapp.AdminEntry.interfaces.NewBloodRequest
import com.example.blooddonationapp.home.interfaces.BloodDonorForm
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
        startDestination = "loadingpage",
        enterTransition = {
            when (initialState.destination.route) {
                "homepage", "userprofile", "notificationspage", "bloodrequests" ->
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(300))
                else ->
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(300))
            }
        },
        exitTransition = {
            when (targetState.destination.route) {
                "homepage", "userprofile", "notificationspage", "bloodrequests" ->
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(300))
                else ->
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(300))
            }
        },
        popEnterTransition = {
            when (initialState.destination.route) {
                "homepage", "userprofile", "notificationspage", "bloodrequests" ->
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(300))
                else ->
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(300))
            }
        },
        popExitTransition = {
            when (targetState.destination.route) {
                "homepage", "userprofile", "notificationspage", "bloodrequests" ->
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(300))
                else ->
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(300))
            }
        },
    ) {

        val mainScreens = listOf("homepage", "userprofile", "notificationspage", "bloodrequests")

        composable(route = "loadingpage",
            enterTransition = {
                scaleIn(
                    initialScale = 0.9f,
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(400))
            },
            exitTransition = {
                scaleOut(
                    targetScale = 1.1f,
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                scaleIn(
                    initialScale = 1.1f,
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(400))
            },
            popExitTransition = {
                scaleOut(
                    targetScale = 0.9f,
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(300))
            },
        ){
            currentPage = "loadingpage"
            loadingpage(
                goto_welcomepage = {navController.navigate("welcomepage") {
                    popUpTo("loadingpage") {inclusive = true}
                } },
                goto_homepage = {navController.navigate("homepage") {
                    popUpTo("loadingpage") {inclusive = true}
                } }
            )
        }

        composable(route = "loginpage",
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                if (targetState.destination.route in mainScreens) {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(300))
                } else {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(300))
                }
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(300))
            },
        ){
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
                    popUpTo(0) { inclusive = true }
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

        // Main screens with bottom navigation bar - use sliding up/down animations
        mainScreens.forEach { route ->
            composable(
                route = route,
                enterTransition = {
                    if (initialState.destination.route in mainScreens) {
                        // Transition between main screens
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            animationSpec = tween(400, easing = FastOutSlowInEasing)
                        ) + fadeIn(animationSpec = tween(300))
                    } else {
                        // Coming from a subscreen or other screen
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Up,
                            animationSpec = tween(400, easing = FastOutSlowInEasing)
                        ) + fadeIn(animationSpec = tween(300))
                    }
                },
                exitTransition = {
                    if (targetState.destination.route in mainScreens) {
                        // Going to another main screen
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            animationSpec = tween(400, easing = FastOutSlowInEasing)
                        ) + fadeOut(animationSpec = tween(300))
                    } else {
                        // Going to a subscreen
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            animationSpec = tween(400, easing = FastOutSlowInEasing)
                        ) + fadeOut(animationSpec = tween(300))
                    }
                },
                popEnterTransition = {
                    if (initialState.destination.route in mainScreens) {
                        // Popping between main screens
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            animationSpec = tween(400, easing = FastOutSlowInEasing)
                        ) + fadeIn(animationSpec = tween(300))
                    } else {
                        // Coming back from a subscreen
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            animationSpec = tween(400, easing = FastOutSlowInEasing)
                        ) + fadeIn(animationSpec = tween(300))
                    }
                },
                popExitTransition = {
                    if (targetState.destination.route in mainScreens) {
                        // Popping to another main screen
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            animationSpec = tween(400, easing = FastOutSlowInEasing)
                        ) + fadeOut(animationSpec = tween(300))
                    } else {
                        // Popping to a non-main screen
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Down,
                            animationSpec = tween(400, easing = FastOutSlowInEasing)
                        ) + fadeOut(animationSpec = tween(300))
                    }
                },
            ) {
                currentPage = route
                when (route) {
                    "homepage" -> homepage(
                        goto_registration = {navController.navigate("ageverification")},
                        goto_aadharregistration = {navController.navigate("verifyaadhar")},
                        goto_bloodreqform = { navController.navigate("bloodreqform") },
                        goto_donorform = {navController.navigate("donorform")}
                    )
                    "userprofile" -> userProfile(
                        goto_loadingpage = { navController.navigate("loadingpage") },
                        goto_settings = { navController.navigate("settingsandpreferences") },
                        goto_aboutus = { navController.navigate("aboutus") },
                        goto_ageverification = {navController.navigate("ageverification")}
                    )
                    "notificationspage" -> NotificationsPage({ navController.navigate("homepage") })
                    "bloodrequests" -> bloodRequests({ navController.navigate("homepage") })
                }
            }
        }

        composable(route = "signuppage",
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(300))
            },
            exitTransition = {
                if (targetState.destination.route in mainScreens) {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(300))
                } else {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(300))
                }
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(400, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(300))
            },
        ){
            currentPage = "signuppage"
            signuppage(
                goto_homepage = {navController.navigate("homepage") {
                    popUpTo(0) {inclusive = true}
                } },
                goto_loginpage = {navController.navigate("loginpage")},
                goto_loadingpage = {navController.navigate("loadingpage")})
        }

        // Sequential registration flow screens - use left/right animations for a smooth flow
        val registrationScreens = listOf("ageverification", "donordetails", "bloodgroup", "verifyaadhar")

        registrationScreens.forEach { route ->
            composable(
                route = route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(450, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(350))
                },
                exitTransition = {
                    val index = registrationScreens.indexOf(route)
                    val targetIndex = registrationScreens.indexOf(targetState.destination.route)
                    if (targetState.destination.route in registrationScreens && targetIndex > index) {
                        // Moving forward in registration flow
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Start,
                            animationSpec = tween(450, easing = FastOutSlowInEasing)
                        ) + fadeOut(animationSpec = tween(350))
                    } else {
                        // Moving backward or to non-registration screen
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.End,
                            animationSpec = tween(450, easing = FastOutSlowInEasing)
                        ) + fadeOut(animationSpec = tween(350))
                    }
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(450, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(350))
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(450, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(350))
                },
            ) {
                currentPage = route
                when (route) {
                    "ageverification" -> ageVerification(goto_donordetails = { navController.navigate("donordetails") })
                    "donordetails" -> donorDetails(goto_bloodgroup = { navController.navigate("bloodgroup") })
                    "bloodgroup" -> BloodGroup(goto_verifyadhaar = { navController.navigate("verifyaadhar") })
                    "verifyaadhar" -> verifyAadhar(goto_homepage = { navController.navigate("homepage") })
                }
            }
        }

        // Supporting screens - slide in from sides
        val supportScreens = listOf("settingsandpreferences", "aboutus")

        supportScreens.forEach { route ->
            composable(
                route = route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(300))
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(300))
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeIn(animationSpec = tween(300))
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                    ) + fadeOut(animationSpec = tween(300))
                },
            ) {
                currentPage = route
                when (route) {
                    "settingsandpreferences" -> SettingsAndPreferences(
                        goto_loadingpage = { navController.navigate("loadingpage") },
                        goto_userProfile = { navController.navigate("userprofile") }
                    )
                    "aboutus" -> AboutUs(goto_userProfile = { navController.navigate("userprofile") })
                }
            }
        }
        composable("bloodreqform"){
            currentPage= "bloodreqform"
            BloodRequestForm(
                goto_parentpage = {navController.navigate("loadingpage")}
            )
        }
        composable("adminloginpage"){
            currentPage= "adminloginpage"
            AdminLoginPage(
                goto_adminpannel = {navController.navigate("adminpannel"){
                    popUpTo("adminloginpage"){inclusive= true}
                } }
            )
        }
        composable("adminpannel"){
            currentPage= "adminpannel"
            AdminHomepage(
                goto_bloodreqverification = {navController.navigate("bloodreqverification")},
                goto_aadharverification = { navController.navigate("aadharverification") },
                goto_newbloodreqpage = { navController.navigate("newbloodreqpage") },
                goto_newannouncementpage = { navController.navigate("newannouncementpage") },
                goto_activebloodrequestspage = { navController.navigate("activebloodreqpage") },
                goto_activeannouncementpage = {navController.navigate("activeannouncementpage")},
                goto_activeadminpasskeys = {navController.navigate("activeadminpasskeys")},
                goto_emergencynumbers = {navController.navigate("emergencynumbers")}
            )
        }
        composable("welcomepage"){
            currentPage= "welcomepage"
            WelcomePage(
                goto_bloodreqform = {navController.navigate("bloodreqform")},
                goto_loginpage = {navController.navigate("loginpage")},
                goto_adminloginpage = {navController.navigate("adminloginpage")}
            )
        }
        composable("aadharverification"){
            currentPage = "aadharverification"
            AadharVerificationPage()
        }
        composable("newbloodreqpage"){
            currentPage = "newbloodreqpage"
            NewBloodRequest(
                goto_activeBloodreqs = {navController.navigate("activebloodreqpage")}
            )
        }
        composable("newannouncementpage"){
            currentPage = "newannouncementpage"
            NewAnnouncement(
                goto_activeAnnouncements = {navController.navigate("activeannouncementpage")}
            )
        }
        composable("activebloodreqpage"){
            currentPage = "activebloodreqpage"
            ActiveBloodRequests()
        }
        composable("activeannouncementpage"){
            currentPage = "activeannouncementpage"
            ActiveAnnouncements()
        }
        composable("activeadminpasskeys"){
            currentPage = "activeadminpasskeys"
            AdminPasskeys()
        }
        composable("bloodreqverification"){
            currentPage = "bloodreqverification"
            BloodReqVerificationPage()
        }
        composable("emergencynumbers"){
            currentPage = "emergencynumbers"
            EmergencyNumbers()
        }

        composable("donorform"){
            currentPage = "donorform"
            BloodDonorForm()
        }
    }
}