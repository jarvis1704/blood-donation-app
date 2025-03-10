package com.example.blooddonationapp.auth.interfaces

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.R
import com.example.blooddonationapp.auth.data.EmailLoginViewModel
import com.example.blooddonationapp.auth.data.SignInState
import com.example.blooddonationapp.auth.data.isPasswordShown
import com.example.blooddonationapp.auth.data.tempUserObj.email
import com.example.blooddonationapp.auth.data.tempUserObj.password

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginpage(
    goto_homepage:()->Unit,
    goto_signuppage:()->Unit,
    goto_loadingpage:()->Unit,
    goto_bloodreqform:()->Unit,
    goto_adminloginpage: () -> Unit,
    state: SignInState,
    onSignInClick:()->Unit,
    viewModel: EmailLoginViewModel = hiltViewModel()
){
//    var viewModel:EmailLoginViewModel = viewModel()

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

    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xFFEB4335))){ //Red BackGround
        Column(
             horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp, horizontal = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = "Login",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(40.dp))
            //Login Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 6.dp
                ),
                onClick = { /*TODO*/ }) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    //Email Feild
                    Text(text = "Email", color = Color.DarkGray, fontSize = 16.sp)
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("example@gmail.com") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                    // Password Field
                    Text(
                        text = "Password",
                        color = Color.DarkGray,
                        fontSize = 16.sp
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Password") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp),
                        visualTransformation = if (isPasswordShown) VisualTransformation.None else PasswordVisualTransformation(),
                        singleLine = true,
                        trailingIcon = {
                                IconButton(
                                    onClick = { isPasswordShown=!isPasswordShown}
                                ) {
                                    Icon(
                                        imageVector = if (isPasswordShown) {
                                            Icons.Filled.AccountCircle
                                        } else {
                                            Icons.Filled.AccountCircle
                                        },
                                        contentDescription = if (isPasswordShown) "Hide password" else "Show password"
                                    )
                                }
                            }
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Forgot Password
                        Text(text = "Forgot Password?",
                            modifier = Modifier.clickable {
                                //todo forgot password
                            },
                            color = Color.DarkGray,
                            fontSize = 14.sp
                        )
                    }
                    Button(onClick = {
                        viewModel.login(email, password, goto_homepage)
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEB4335)
                        ),
                        shape = RoundedCornerShape(8.dp)) {
                        Text(text = "Login", color = Color.White,
                            fontSize = 16.sp)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center
                ) {
                    // Create Account Link
                    Text(text = "New here? Create an Account",
                        color = Color.Black,
                        fontSize = 16.sp,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            goto_signuppage()
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            Text(text = "Or", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            //Google SignUp
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .height(50.dp)
                    .clickable(onClick = { onSignInClick() }),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google_icon),
                        contentDescription = "Google Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Continue with Google",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }
            TextButton(
                onClick = {
                    goto_adminloginpage()
                }
            ) {
                Text("Admin? Click Here")
            }
            Button(
                onClick = {
                    goto_bloodreqform()
                }
            ) {
                Text("Need emergency blood? Click here!")
            }
        }
    }
}