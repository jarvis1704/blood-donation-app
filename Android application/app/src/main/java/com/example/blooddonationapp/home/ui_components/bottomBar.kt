package com.example.blooddonationapp.home.ui_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
            NavigationBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 3.dp
            ) {
                // Home Item
                NavigationBarItem(
                    selected = currentPage == "homepage",
                    onClick = goto_homepage,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        indicatorColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                )

                // Blood Requests Item
                NavigationBarItem(
                    selected = currentPage == "bloodrequests",
                    onClick = goto_bloodrequests,
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_blood_drop),
                            contentDescription = "Blood Requests"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        indicatorColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                )

                // Profile Item
                NavigationBarItem(
                    selected = currentPage == "userprofile",
                    onClick = goto_userprofile,
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        indicatorColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                )
            }
        }
    }
}