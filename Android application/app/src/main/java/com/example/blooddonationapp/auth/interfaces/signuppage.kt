package com.example.blooddonationapp.auth.interfaces


import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.auth.data.EmailLoginViewModel
import com.example.blooddonationapp.auth.data.isPasswordShown
import com.example.blooddonationapp.auth.data.tempUserObj




@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun signuppage(
    goto_homepage: () -> Unit,
    goto_loginpage: () -> Unit,
    goto_loadingpage: () -> Unit,
    viewModel: EmailLoginViewModel = hiltViewModel()
) {
//    var viewModel: EmailLoginViewModel = viewModel()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .background(Color(0xFFEB4335))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(24.dp))
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
                    .weight(0.8f)
                    .background(Color.White)
            ){
                Card(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(horizontal = 16.dp)
                        .align(Alignment.Center).offset(y = (-38).dp),
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
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0xFFF5F5F5),
                                focusedContainerColor = Color(0xFFF5F5F5),
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
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0xFFF5F5F5),
                                focusedContainerColor = Color(0xFFF5F5F5),
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
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0xFFF5F5F5),
                                focusedContainerColor = Color(0xFFF5F5F5),
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
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0xFFF5F5F5),
                                focusedContainerColor = Color(0xFFF5F5F5),
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
                                viewModel.signup(tempUserObj.email, tempUserObj.password, tempUserObj.name, tempUserObj.confirmpassword, goto_loadingpage)
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

    }
}



