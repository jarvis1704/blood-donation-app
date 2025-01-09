package com.example.blooddonationapp.tempAdminEntry

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blooddonationapp.global.data.errorMessage

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun adminPage(){
    var viewmodel:adminViewmodel = viewModel()
    Column (
        modifier = Modifier.fillMaxSize().padding(50.dp),
    ){
        Text("new blood req:")
        TextField(
            value = newBloodRequest.bloodgroup,
            onValueChange = {
                newBloodRequest.bloodgroup = it
            }, placeholder = {
                Text("Blood group")
            })
        TextField(
            value = newBloodRequest.hospital,
            onValueChange = {
                newBloodRequest.hospital = it
            }, placeholder = {
                Text("Hospital")
            })
        TextField(
            value = newBloodRequest.details,
            onValueChange = {
                newBloodRequest.details = it
            }, placeholder = {
                Text("details (optional)")
            })
        Button(
            onClick = {
                if (newBloodRequest.bloodgroup != "" && newBloodRequest.hospital != ""){
                    //todo new notif
                    viewmodel.newNotification()
                }
                else{
                    errorMessage = "Error: multiple entries are empty"
                }
            }
        ) {
            Text("Push")
        }
    }
}