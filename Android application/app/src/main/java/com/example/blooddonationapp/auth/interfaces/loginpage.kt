package com.example.blooddonationapp.auth.interfaces

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.auth.data.SignInState
import com.example.blooddonationapp.auth.data.emailLoginViewmodel
import com.example.blooddonationapp.auth.data.isPasswordShown
import com.example.blooddonationapp.auth.data.tempUserObj

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginpage(
    goto_homepage:()->Unit,
    goto_signuppage:()->Unit,
    state: SignInState,
    onSignInClick:()->Unit
){
    var viewmodel:emailLoginViewmodel = viewModel()

    //does something something, idk
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login")

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(15.dp),
                onClick = { /*TODO*/ }) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Text(text = "Email")
                    TextField(
                        value = tempUserObj.email,
                        onValueChange = {
                            tempUserObj.email = it
                        }, placeholder = {
                            Text(text = "example@gmail.com")
                        })
                    Text(text = "Password")
                    TextField(
                        value = tempUserObj.password,
                        onValueChange = {
                            tempUserObj.password = it
                        }, placeholder = {
                            Text(text = "Password")
                        }, visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation())
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        RadioButton(
                            selected = isPasswordShown,
                            onClick = {
                                isPasswordShown = !isPasswordShown
                            })
                        Text(text = "Show Password")
                    }
                    Text(text = "Forgot Password?",
                        modifier = Modifier.clickable {
                            //todo forgot password
                        })
                    Button(onClick = {
                        viewmodel.login(tempUserObj.email, tempUserObj.password, goto_homepage)
                    }) {
                        Text(text = "Login")
                    }
                }
                Text(text = "Or")
                Button(onClick = {
                    onSignInClick()
                }) {
                    Text(text = "Google")
                }
                Text(text = "Create an account",
                    modifier = Modifier.clickable {
                        //todo signup page
                    })
            }
        }
    }
}