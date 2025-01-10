package com.example.blooddonationapp.home.ui_components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blooddonationapp.R
import com.example.blooddonationapp.global.data.currentPage

@Composable
fun bottomBar(
    goto_homepage:()->Unit,
    goto_bloodrequests:()->Unit,
    goto_userprofile:()->Unit,
){
    when (currentPage) {
        "homepage", "bloodrequests", "userprofile", "notificationspage" -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    color = Color.White,
                    tonalElevation = 3.dp,
                    shadowElevation = 3.dp
                ) {
                    NavigationBar(
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = Color.Transparent,
                        tonalElevation = 0.dp,
                        windowInsets = WindowInsets(0.dp)
                    ) {
                        NavigationBarItem(
                            selected = currentPage == "homepage",
                            onClick = goto_homepage,
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Home",
                                    tint = if (currentPage == "homepage")
                                        Color(0xFFEB4335)
                                    else
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                            )
                        )

                        NavigationBarItem(
                            selected = currentPage == "bloodrequests",
                            onClick = goto_bloodrequests,
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_blood_drop),
                                    contentDescription = "Blood Requests",
                                    tint = if (currentPage == "bloodrequests")
                                        Color(0xFFEB4335)
                                    else
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent
                            )
                        )

                        NavigationBarItem(
                            selected = currentPage == "userprofile",
                            onClick = goto_userprofile,
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Profile",
                                    tint = if (currentPage == "userprofile")
                                        Color(0xFFEB4335)
                                    else
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    }
}