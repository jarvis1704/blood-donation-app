package com.example.blooddonationapp.auth.interfaces

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.auth.data.emailLoginViewmodel
import com.example.blooddonationapp.auth.data.isPasswordShown
import com.example.blooddonationapp.auth.data.tempUserObj

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun signuppage(
    goto_homepage:()->Unit,
    goto_loginpage:()->Unit,
    goto_loadingpage:()->Unit
){
    var viewmodel: emailLoginViewmodel = viewModel()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Create Account")
            Text(text = "Enter your account details")

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
                    Text(text = "Full Name")
                    TextField(
                        value = tempUserObj.name,
                        onValueChange = {
                            tempUserObj.name = it
                        }, placeholder = {
                            Text(text = "Mr XYZ")
                        })
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
                        },
                        placeholder = {
                            Text(text = "Password")
                        },
                        visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation()
                    )
                    Text(text = "Confirm Password")
                    TextField(
                        value = tempUserObj.confirmpassword,
                        onValueChange = {
                            tempUserObj.confirmpassword = it
                        }, placeholder = {
                            Text(text = "Password")
                        }, visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation())

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = isPasswordShown,
                            onClick = {
                                isPasswordShown = !isPasswordShown
                            })
                        Text(text = "Show Password")
                    }

                    Button(onClick = {
                        viewmodel.signup(tempUserObj.email, tempUserObj.password, tempUserObj.confirmpassword, goto_loadingpage)
                    }) {
                        Text(text = "Next")
                    }

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(text = "Already have an account?")
                        Text(text = " Login ",
                            modifier = Modifier.clickable {
                                goto_loginpage()
                            })
                    }
                }
            }
        }
    }
}