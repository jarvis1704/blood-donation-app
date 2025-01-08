package com.example.blooddonationapp.home.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.blooddonationapp.global.data.currentPage

@Composable
fun bottomBar(
    goto_homepage:()->Unit,
    goto_bloodrequests:()->Unit,
    goto_userprofile:()->Unit,
){
    when(currentPage){
        "homepage", "bloodrequests", "userprofile", "notificationspage"->{
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(onClick = {
                        goto_homepage()
                    }) {       //homepage
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null)
                    }
                    Button(onClick = {
                        goto_bloodrequests()
                    }) {       //blood req
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null)
                    }
                    Button(onClick = {
                        goto_userprofile()
                    }) {       //profile
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null)
                    }
                }
            }
        }
    }
}