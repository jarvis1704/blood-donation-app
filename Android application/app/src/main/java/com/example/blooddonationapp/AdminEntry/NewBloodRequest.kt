package com.example.blooddonationapp.AdminEntry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NewBloodRequest(
    adminViewmodel: AdminViewmodel = hiltViewModel()
){
    Column (
        modifier = Modifier.padding(16.dp)
    ){
        Spacer(Modifier.height(16.dp))
        Text("New Blood Request")
        Spacer(Modifier.height(16.dp))
        Text("Patient Name")
        TextField(
            value = newBloodRequest.patientname,
            onValueChange = {
                newBloodRequest.patientname = it
            }
        )
        Text("Attendant Name")
        TextField(
            value = newBloodRequest.attendantname,
            onValueChange = {
                newBloodRequest.attendantname = it
            }
        )
        Text("Attendant Phone No")
        TextField(
            value = newBloodRequest.attendantphoneno,
            onValueChange = {
                newBloodRequest.attendantphoneno = it
            }
        )
        Text("Hospital")
        TextField(
            value = newBloodRequest.hospital,
            onValueChange = {
                newBloodRequest.hospital = it
            }
        )
        Text("Blood Group")
        TextField(
            value = newBloodRequest.bloodgroup,
            onValueChange = {
                newBloodRequest.bloodgroup = it
            }
        )
        Text("Details (Optional)")
        TextField(
            value = newBloodRequest.details,
            onValueChange = {
                newBloodRequest.details = it
            }
        )
        Button(
            onClick = {
                adminViewmodel.newBloodReq()
            }
        ) {
            Text("Publish")
        }
    }
}