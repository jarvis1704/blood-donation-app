package com.example.blooddonationapp.auth.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blooddonationapp.global.data.PhoneNoList
import org.jetbrains.annotations.NotNull

@Composable
fun WelcomePage(
    goto_bloodreqform:()->Unit,
    goto_loginpage:()->Unit,
    goto_adminloginpage:()->Unit
){
    Box(
        modifier = Modifier.fillMaxSize(),
    ){
        Column (
            Modifier.fillMaxSize(),
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .background(Color(0xFFEB4335))
            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(Modifier.height(60.dp))
                    Text(
                        "Welcome to Blood donation app",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    //helpline card
                    Card (
                        modifier = Modifier.fillMaxWidth(0.7f).padding(vertical = 20.dp)
                    ){
                        Column (
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            when(true){
                                PhoneNoList.isNotEmpty()  -> {
                                    Text("Our helpline numbers:")
                                    Spacer(modifier = Modifier.height(32.dp))
                                    PhoneNoList.forEach { no->
                                        Text(no.name+": "+no.number)
                                    }
                                }
                                else -> {
                                    Text("No emergency numbers available at this moment")
                                }
                            }
                        }
                    }

                    Button(onClick = {
                        goto_bloodreqform()
                    } ,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEB4335)
                        ),
                    ) {
                        Text("Need Emergency Blood? Click here!", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.height(16.dp))
                    Text("Or", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = {
                        goto_loginpage()
                    } ,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEB4335)
                        ),
                    ) {
                        Text("Login/Signup", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.height(16.dp))
                    TextButton(onClick = {
                        goto_adminloginpage()
                    }) {
                        Text("Admin? Click Here", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}