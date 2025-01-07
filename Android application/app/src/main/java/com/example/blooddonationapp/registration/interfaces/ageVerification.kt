package com.example.blooddonationapp.registration.interfaces

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.blooddonationapp.registration.ui_components.dateYearSelector
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ageVerification(goto_donordetails:()->Unit){
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Age verification")
            Text(text = "Please enter your birth date")
            dateYearSelector(
                selectedDate = LocalDate.now())
            Button(onClick = {
                //todo save date

                goto_donordetails()
            }) {
                Text(text = "Next")
            }
        }
    }
}