package com.example.blooddonationapp.auth.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blooddonationapp.global.data.PhoneNoList
import org.jetbrains.annotations.NotNull

// Define color constants for consistency
private val primaryRed = Color(0xFFEB4335)
private val lightRed = Color(0xFFF5948C)
private val paleRed = Color(0xFFFAD5D1)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WelcomePage(
    goto_bloodreqform: () -> Unit,
    goto_loginpage: () -> Unit,
    goto_adminloginpage: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            Modifier.fillMaxSize(),
        ) {
            // Header section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .weight(0.2f)
                    .background(primaryRed)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(60.dp))
                    Text(
                        "Welcome to Blood Donation App",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Content section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .weight(0.8f)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(Modifier.height(20.dp))
                    // Helpline card with improved styling
                    Card(
                        modifier = Modifier
                            .width(320.dp)
                            .padding(vertical = 24.dp)
                            .border(5.dp, primaryRed, RoundedCornerShape(16.dp))
                            .shadow(8.dp, RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color.White
                                )
                                .padding(4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Column (
                                    horizontalAlignment = Alignment.Start
                                ){
                                    Text(
                                        when (PhoneNoList.isNotEmpty()) {
                                            true -> "Our Helpline Numbers"
                                            else -> "No Emergency Numbers Available"
                                        },
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = Color(0xFF333333)
                                    )

                                    Spacer(modifier = Modifier.height(18.dp))

                                    if (PhoneNoList.isNotEmpty()) {
                                        FlowRow (
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            maxItemsInEachRow = 2
                                        ){
                                            PhoneNoList.forEach { no ->

                                                Column (
                                                    modifier = Modifier.fillMaxWidth(0.45f).padding(bottom = 8.dp)
                                                ){
                                                    Text(
                                                        no.name,
                                                        fontSize = 16.sp,
                                                        fontWeight = FontWeight.Medium,
                                                        lineHeight = 20.sp,
                                                        textAlign = TextAlign.Start,
                                                        color = Color.Black.copy(0.6f),
                                                        modifier = Modifier.padding(top = 4.dp)
                                                    )
                                                    Text(
                                                        no.number,
                                                        fontSize = 16.sp,
                                                        lineHeight = 20.sp,
                                                        fontWeight = FontWeight.Medium,
                                                        textAlign = TextAlign.Center,
                                                        modifier = Modifier.padding(vertical = 4.dp)
                                                    )
                                                }
                                            }

                                        }
                                    }
//                                else {
//                                    Text(
//                                        "Please check back later for assistance contact information",
//                                        textAlign = TextAlign.Center,
//                                        fontSize = 16.sp
//                                    )
//                                }
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(10.dp))


                    // Emergency blood button with improved styling
                    Button(
                        onClick = { goto_bloodreqform() },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryRed
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "Need Emergency Blood? Click here!",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(Modifier.height(20.dp))

                    Text(
                        "Or",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )

                    Spacer(Modifier.height(20.dp))

                    // Login button with improved styling
                    Button(
                        onClick = { goto_loginpage() },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryRed
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "Login / Signup",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(Modifier.height(20.dp))

                    // Admin login button with improved styling
                    TextButton(
                        onClick = { goto_adminloginpage() },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            "Admin? Click Here",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = primaryRed
                        )
                    }
                    Spacer(Modifier.height(60.dp))
                    //an idea to increase our teams reach
                    Text(
                        "Made with ❤️ by Prudam, Biprangshu, Chayan and Sujal",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}