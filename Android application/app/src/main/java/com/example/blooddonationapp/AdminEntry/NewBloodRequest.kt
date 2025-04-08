package com.example.blooddonationapp.AdminEntry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.registration.data.tempRegistrationDetails
import com.example.blooddonationapp.registration.interfaces.AnimatedBloodGroupButton

@Composable
fun NewBloodRequest(
    adminViewmodel: AdminViewmodel = hiltViewModel()
){
    val positiveBloodGroups = listOf("A+", "B+", "AB+", "O+")
    val negativeBloodGroups = listOf("A-", "B-", "AB-", "O-")

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
        Text("Patient Age and Gender")
        TextField(
            value = newBloodRequest.patientage,
            onValueChange = {
                newBloodRequest.patientage = it
            }
        )
        TextField(
            value = newBloodRequest.patientgender,
            onValueChange = {
                newBloodRequest.patientgender = it
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
        //blood group selector
        Card(
            modifier = Modifier.fillMaxWidth(0.95f),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(8.dp)
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
            {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                      Added loop with list for repeated buttons
                    positiveBloodGroups.forEach { bloodType ->
                        AnimatedBloodGroupButton(
                            bloodType = bloodType,
                            isSelected = newBloodRequest.bloodgroup == bloodType,
                            onClick = { newBloodRequest.bloodgroup = bloodType }
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                        Similarly here
                    negativeBloodGroups.forEach { bloodgroup ->
                        AnimatedBloodGroupButton(
                            bloodType = bloodgroup,
                            isSelected = newBloodRequest.bloodgroup == bloodgroup,
                            onClick = { newBloodRequest.bloodgroup = bloodgroup }
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Text("Optional:")
        Text("Urgency Level")
        Text("Units of Blood Required")
        TextField(
            value = newBloodRequest.unitsrequired,
            onValueChange = {
                newBloodRequest.unitsrequired= it
            }
        )
        Text("Extra Details")
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