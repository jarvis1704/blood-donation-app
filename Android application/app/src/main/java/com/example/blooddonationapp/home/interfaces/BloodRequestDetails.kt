package com.example.blooddonationapp.home.interfaces

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.blooddonationapp.home.data.bloodRequest
import com.example.blooddonationapp.home.data.requestToShow

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BloodRequestDetails(){
    Column (
        modifier = Modifier.padding(50.dp)
    ){
        Text("this is blood req details page")
        Text(requestToShow.patientname+requestToShow.bloodtype)
    }
}