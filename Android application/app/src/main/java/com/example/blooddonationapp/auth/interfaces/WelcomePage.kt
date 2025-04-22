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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
                    .weight(0.2f)
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
                    .weight(0.8f)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    // Helpline card with improved styling
                    Card(
                        modifier = Modifier
                            .size(width = 320.dp, height = 250.dp)
                            .padding(vertical = 24.dp)
                            .shadow(8.dp, RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(lightRed, paleRed)
                                    )
                                )
                                .padding(4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    when (PhoneNoList.isNotEmpty()) {
                                        true -> "Our Helpline Numbers"
                                        else -> "No Emergency Numbers Available"
                                    },
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color(0xFF333333)
                                )

                                Spacer(modifier = Modifier.height(32.dp))

                                if (PhoneNoList.isNotEmpty()) {
                                    PhoneNoList.forEach { no ->
                                        Text(
                                            "${no.name}: ${no.number}",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        )
                                    }
                                } else {
                                    Text(
                                        "Please check back later for assistance contact information",
                                        textAlign = TextAlign.Center,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }

                    // Emergency blood button with improved styling
                    Button(
                        onClick = { goto_bloodreqform() },
                        modifier = Modifier
                            .fillMaxWidth()
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
                            .fillMaxWidth()
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