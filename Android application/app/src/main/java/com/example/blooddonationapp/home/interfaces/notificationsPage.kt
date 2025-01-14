package com.example.blooddonationapp.home.interfaces

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.home.data.TimestampToLocalDate
import com.example.blooddonationapp.home.data.globalNotificationList
import com.example.blooddonationapp.home.data.homeViewmodel
import com.example.blooddonationapp.home.data.newNotificationsCounter
import com.example.blooddonationapp.home.data.notification
import com.example.blooddonationapp.ui.theme.BloodDonationAppColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun notificationsPage(){
    //when user enters this page, store the timestamp, we will use that timestamp to look for new notifications
    var homeViewmodel:homeViewmodel = viewModel()
    CoroutineScope(Dispatchers.IO).launch {
        homeViewmodel.updateNotificationLastSeen()
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White).statusBarsPadding()
    ){

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 25.dp, horizontal = 24.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton({}) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back Button")
                }
                Text("NOTIFICATIONS")
            }
            Text(newNotificationsCounter.toString()+" New Notifications", Modifier.padding(horizontal = 16.dp))
            LazyColumn (
                modifier = Modifier.fillMaxSize().padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){

                globalNotificationList?.let {
                    itemsIndexed(it.toList()){ item, index ->
                        showNotification(item, index)
                    }
                }
            }
        }
    }
    // i think a filter option will be nice for blood type of user
}

@SuppressLint("NewApi")
@Composable
fun showNotification(index: Int, data:notification){
    val notificationIconColor= if (index < newNotificationsCounter) BloodDonationAppColor.BloodRed else Color.LightGray
    Box(modifier = Modifier.fillMaxWidth()){
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "Notification Icon", tint = notificationIconColor)
                Text("Blood group:"+data.bloodtype+"is required at hospital:"+data.hospital)
            }

            Text("${data.date?.dayOfMonth}, ${data.date?.month}", modifier = Modifier.padding(horizontal = 26.dp))
            HorizontalDivider(thickness = 1.dp)
        }
    }
}