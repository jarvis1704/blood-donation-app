package com.example.blooddonationapp.home.interfaces

import android.os.Build
import android.telephony.emergency.EmergencyNumber
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.blooddonationapp.global.data.updateCurrentUser

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EmergencyContacts(){
    updateCurrentUser()
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text("this is emergency contacts page")
    }
}