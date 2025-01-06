package com.example.blooddonationapp.auth.interfaces


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.auth.data.emailLoginViewmodel
import com.example.blooddonationapp.auth.data.isPasswordShown
import com.example.blooddonationapp.auth.data.tempUserObj
import kotlin.math.sin


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun signuppage(
//    goto_homepage:()->Unit,
//    goto_loginpage:()->Unit,
//    goto_loadingpage:()->Unit
//){
//    var viewmodel: emailLoginViewmodel = viewModel()
//
//    Box(modifier = Modifier.fillMaxSize()) {
//        Column(
//            modifier = Modifier.fillMaxSize()
////            horizontalAlignment = Alignment.CenterHorizontally,
////            verticalArrangement = Arrangement.Center
//        ) {
////            Text(text = "Create Account")
////            Text(text = "Enter your account details")
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(0.4f)
//                    .background(Color(0xFFEB4335))
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .statusBarsPadding()
//                        .padding(top = 40.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(
//                        "Create Account",
//                        fontSize = 32.sp,
//                        color = Color.White,
//                        fontWeight = FontWeight.Bold
//                    )
//                    Spacer(Modifier.height(8.dp))
//                    Text(
//                        "Enter your Account Details",
//                        fontSize = 16.sp,
//                        color = Color.White,
//                        fontWeight = FontWeight.Medium
//                    )
//                }
//                //White background
//                Box(
//                    modifier = Modifier.fillMaxWidth().background(Color.White)
//                )
//            }
//
//            //Form Card
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth(0.9f)
//                    .padding(15.dp),
//                onClick = { /*TODO*/ }) {
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(15.dp)
//                ) {
//                    Text(text = "Full Name")
//                    TextField(
//                        value = tempUserObj.name,
//                        onValueChange = {
//                            tempUserObj.name = it
//                        }, placeholder = {
//                            Text(text = "Mr XYZ")
//                        })
//                    Text(text = "Email")
//                    TextField(
//                        value = tempUserObj.email,
//                        onValueChange = {
//                            tempUserObj.email = it
//                        }, placeholder = {
//                            Text(text = "example@gmail.com")
//                        })
//                    Text(text = "Password")
//                    TextField(
//                        value = tempUserObj.password,
//                        onValueChange = {
//                            tempUserObj.password = it
//                        },
//                        placeholder = {
//                            Text(text = "Password")
//                        },
//                        visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation()
//                    )
//                    Text(text = "Confirm Password")
//                    TextField(
//                        value = tempUserObj.confirmpassword,
//                        onValueChange = {
//                            tempUserObj.confirmpassword = it
//                        }, placeholder = {
//                            Text(text = "Password")
//                        }, visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation())
//
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        RadioButton(
//                            selected = isPasswordShown,
//                            onClick = {
//                                isPasswordShown = !isPasswordShown
//                            })
//                        Text(text = "Show Password")
//                    }
//
//                    Button(onClick = {
//                        viewmodel.signup(tempUserObj.email, tempUserObj.password, tempUserObj.confirmpassword, goto_loadingpage)
//                    }) {
//                        Text(text = "Next")
//                    }
//
//                    Row (
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically
//                    ){
//                        Text(text = "Already have an account?")
//                        Text(text = " Login ",
//                            modifier = Modifier.clickable {
//                                goto_loginpage()
//                            })
//                    }
//                }
//            }
//        }
//    }
//

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun signuppage(
    goto_homepage: () -> Unit,
    goto_loginpage: () -> Unit,
    goto_loadingpage: () -> Unit
) {
    var viewmodel: emailLoginViewmodel = viewModel()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f)
                    .background(Color(0xFFEB4335))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(top = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Create Account",
                        fontSize = 32.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Enter your Account Details",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f)
                    .background(Color.White)
            )

        }
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(horizontal = 16.dp)
                .align(Alignment.Center),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text("Full Name", fontWeight = FontWeight.Medium)
                TextField(
                    value = tempUserObj.name,
                    onValueChange = { tempUserObj.name = it },
                    placeholder = { Text("Mr XYZ") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFF5F5F5),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color(0xFFEB4335),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        disabledPlaceholderColor = Color.LightGray
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Email", fontWeight = FontWeight.Medium)
                TextField(
                    value = tempUserObj.email,
                    onValueChange = { tempUserObj.email = it },
                    placeholder = { Text("example@gmail.com") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFF5F5F5),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color(0xFFEB4335),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        disabledPlaceholderColor = Color.LightGray
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Password", fontWeight = FontWeight.Medium)
                TextField(
                    value = tempUserObj.password,
                    onValueChange = { tempUserObj.password = it },
                    placeholder = { Text("Password") },
                    visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFF5F5F5),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color(0xFFEB4335),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        disabledPlaceholderColor = Color.LightGray
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text("Confirm Password", fontWeight = FontWeight.Medium)
                TextField(
                    value = tempUserObj.confirmpassword,
                    onValueChange = { tempUserObj.confirmpassword = it },
                    placeholder = { Text("Password") },
                    visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFF5F5F5),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color(0xFFEB4335),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        disabledPlaceholderColor = Color.LightGray
                    ),
                    singleLine = true
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isPasswordShown,
                        onClick = { isPasswordShown = !isPasswordShown},
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFFEB4335)
                        )
                    )
                    Text("Show Password")
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        viewmodel.signup(tempUserObj.email, tempUserObj.password, tempUserObj.confirmpassword, goto_loadingpage)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEB4335))
                ) {
                    Text("Next", color = Color.White, modifier = Modifier.padding(vertical = 4.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Already have an account?")
                    Text(
                        " Login",
                        color = Color(0xFFEB4335),
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable { goto_loginpage() }
                    )
                }
            }
        }
    }
}



