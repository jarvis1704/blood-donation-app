package com.example.blooddonationapp.home.ui_components

import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.blooddonationapp.global.data.currentPage

@Composable
fun notifButton(){
    when(currentPage){
        "homepage", "bloodrequests", "userprofile"->{
            Button(modifier = Modifier.offset(
                x= (-10).dp, y= (-700).dp
            ),
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null)
            }
        }
    }
}