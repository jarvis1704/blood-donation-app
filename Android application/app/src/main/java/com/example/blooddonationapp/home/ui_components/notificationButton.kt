package com.example.blooddonationapp.home.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.blooddonationapp.global.data.currentPage
import com.example.blooddonationapp.home.data.newNotificationsCounter


@Composable
fun notifButton(goto_notifications:()->Unit){
    when(currentPage){
        "homepage", "bloodrequests", "userprofile"->{

            Box {
                IconButton(modifier = Modifier.offset(
                    x= (-7).dp, y= (-685).dp
                ),
                    onClick = { goto_notifications() }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            if (newNotificationsCounter!=0){
                Box {
                    IconButton(modifier = Modifier
                        .clickable(enabled = false, onClick = {})
                        .offset(
                        x= (-17).dp, y= (-691).dp
                    ),
                        onClick = { goto_notifications() }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier.size(21.dp).rotate(90F)
                        )
                    }
                }
            }
            if (newNotificationsCounter!=0){
                Box {
                    IconButton(modifier = Modifier
                        .clickable(enabled = false, onClick = {})
                        .offset(
                            x= (-17).dp, y= (-691).dp
                        ),
                        onClick = { goto_notifications() }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp).rotate(90F)
                        )
                    }
                }
            }
            if (newNotificationsCounter!=0){
                Box {
                    IconButton(modifier = Modifier
                        .clickable(enabled = false, onClick = {})
                        .offset(
                        x= (-17).dp, y= (-691).dp
                    ),
                        onClick = { goto_notifications() }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier.size(15.dp).rotate(180F)
                        )
                    }
                }
            }
        }
    }
}