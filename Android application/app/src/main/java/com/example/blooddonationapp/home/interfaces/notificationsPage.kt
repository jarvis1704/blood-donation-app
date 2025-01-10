package com.example.blooddonationapp.home.interfaces

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.blooddonationapp.home.data.globalNotificationList
import com.example.blooddonationapp.home.data.notification

@Composable
fun notificationsPage(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column (
            modifier = Modifier.fillMaxSize().padding(25.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("NOTIFICATIONS")
            }
            Text("0 New Notifications")
            LazyColumn (
                modifier = Modifier.fillMaxSize()
            ){
                globalNotificationList?.let {
                    items(it.toList()){ item ->
                        showNotification(item)
                    }
                }
            }
        }
    }
    // i think a filter option will be nice for blood type of user
}

@Composable
fun showNotification(data:notification){
    Box(modifier = Modifier.fillMaxWidth()){
        Column (
            modifier = Modifier.fillMaxWidth().padding(20.dp).border(1.dp, Color.Red)
        ){
            Text("Blood group:"+data.bloodtype)
            Text("hospital:"+data.hospital)
        }
    }
}