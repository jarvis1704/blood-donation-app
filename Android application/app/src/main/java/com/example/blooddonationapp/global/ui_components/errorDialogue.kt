package com.example.blooddonationapp.global.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.global.data.isErrorDialogue


@Composable
fun errorAlert(){
    if (errorMessage.isNotEmpty()){
        AlertDialog(
            onDismissRequest = { isErrorDialogue=false },
            title = { Text(text = "Error") },
            text = { Text(text = errorMessage) },
            confirmButton = {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(onClick = {
                        errorMessage = ""
                    }) {
                        Text(text = "Ok")
                    }
                }
            }
        )
    }
}
