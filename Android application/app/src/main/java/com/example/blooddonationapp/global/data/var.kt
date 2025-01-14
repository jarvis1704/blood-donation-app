package com.example.blooddonationapp.global.data

import android.annotation.SuppressLint
import android.icu.text.CaseMap.Title
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.home.data.HomeViewModel
import com.google.firebase.Timestamp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

//to keep track of the current user logged in
object currentUser{
    var isSearching by mutableStateOf(true)
    var isLoggedIn by mutableStateOf(false)

    var registrationType by mutableStateOf("")
    /* we fetch this string at runtime
     null = not registered, so goto registration page
     "signup" = only email and password is registered, other data need to be uploaded
     "registered" = goto homepage directly */

    //below data will be fetched from either email or registration pages
    var birthDate by mutableStateOf("")
    var username by mutableStateOf("User!")
    var gender by mutableStateOf("")
    var area by mutableStateOf("")
    var phoneNo by mutableStateOf(0)
    var lastDonationDate by mutableStateOf("")
    var locationSelected by mutableStateOf("")
    var bloodGroup by mutableStateOf("")
    var adhaarNo by mutableStateOf(0)
    var adhaarDOB by mutableStateOf("")
    var profilePic by mutableStateOf("")
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun updateCurrentUser(){
    var viewmodel: HomeViewModel = viewModel()
    CoroutineScope(Dispatchers.IO).launch {
        currentUser.username = viewmodel.getRegistrationEntryByString("username")
        currentUser.gender = viewmodel.getRegistrationEntryByString("gender")
        currentUser.area = viewmodel.getRegistrationEntryByString("area")
        currentUser.profilePic = viewmodel.getRegistrationEntryByString("profilepic")
        currentUser.bloodGroup = viewmodel.getRegistrationEntryByString("bloodGroup")
        viewmodel.FetchNotifications()
        viewmodel.FetchAnnouncements()
    }
}

//for global error dialogue
var errorMessage by mutableStateOf("")         //just change value to push an alert
var isErrorDialogue by mutableStateOf(false)   //no need to change value, launchedAffect takes care of it

//for global alert dialogue
var isAlertDialogue by mutableStateOf(false)   //need to change value
object GlobalAlert{
    var title by mutableStateOf("")
    var details by mutableStateOf("")
    var onCancelClick:()->Unit = {}
    var onConfirmClick:()->Unit = {}
}
fun NewGlobalAlert(title: String, details:String, onCancelClick:()->Unit={}, onConfirmClick:()->Unit){
    GlobalAlert.title=title
    GlobalAlert.details=details
    GlobalAlert.onCancelClick = onCancelClick
    GlobalAlert.onConfirmClick = onConfirmClick
    isAlertDialogue = true
}

var currentPage by mutableStateOf("")  //universal, handled inside appNav


//change string to timestamp to store in firebase
@RequiresApi(Build.VERSION_CODES.O)
fun stringToTimestamp(data:String):Timestamp?{
    val formatter = DateTimeFormatter.ofPattern("ddMMyyyy")
    try {
        //parse string to LocalDate
        val localDate = LocalDate.parse(data, formatter)

        //convert LocalDate to Date
        val instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        val theDate = Date.from(instant)

        //create Timestamp
        val timestamp = Timestamp(theDate)

        return timestamp
    }catch (e:Exception){
        errorMessage = e.message.toString()
        return null
    }
}

fun checkCorrectDateStringEntered(date:String):Boolean{
    //entered sting is "ddMMyyyy" we need to check if that date is valid
    var ddMMyyyy = date.toLong()
    var yyyy = ddMMyyyy%10000
    var ddMM = (ddMMyyyy - yyyy) / 10000
    var MM = ddMM % 100
    var dd = (ddMM - MM)/100

    if (MM !in 1..12){
        errorMessage="Please enter a valid date"
        return false
    }

    when(MM){
        1L, 3L, 5L, 7L, 8L, 10L, 12L ->{//jan, mar, may, jul, aug, oct, dec
            if (dd in 1..31){
                return true
            }
        }
        4L, 6L, 9L, 11L ->{ //apr, jun, sep, nov
            if (dd in 1..30){
                return true
            }
        }
        2L->{
            if (yyyy%4==0L &&(yyyy % 100 != 0L || yyyy % 400 ==0L)){ //leap year
                if (dd in 1..29){
                    return true
                }
            }else{ //non leap year
                if (dd in 1..28){
                    return true
                }
            }
        }
        else->{
            errorMessage="Please enter a valid date"
            return false
        }
    }
    return false
}