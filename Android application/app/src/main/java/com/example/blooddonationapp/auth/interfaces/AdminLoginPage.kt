package com.example.blooddonationapp.auth.interfaces

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.auth.data.EmailLoginViewModel
import com.example.blooddonationapp.global.data.errorMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Composable
fun AdminLoginPage(
    goto_adminpannel:()->Unit,
    viewModel: EmailLoginViewModel = hiltViewModel()
){
    val coroutineScope = rememberCoroutineScope()
    var Passkey = remember { mutableStateOf("") }
    Column (
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text("Admin Login")
        TextField(
            value = Passkey.value,
            onValueChange = {
                Passkey.value = it
            },
            placeholder = { Text("Enter Passkey") }
        )
        Button(
            onClick = {
                if (Passkey.value.isEmpty()){
                    errorMessage = "Error: Empty Field"
                }
                else{
                    coroutineScope.launch {
                        viewModel.CheckAdminPasskey(Passkey.value, goto_adminpannel)
                    }
                }
            }
        ) {
            Text("Continue")
        }
    }
}