package com.example.blooddonationapp.AdminEntry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun AdminPasskeys(
    adminViewmodel: AdminViewmodel = hiltViewModel()
){
    LaunchedEffect(Unit) {
        isFetchingPasskeys = true
        adminViewmodel.GetActivePasskeys()
    }
    Column (
        modifier = Modifier.padding(16.dp)
    ){
        Spacer(Modifier.height(16.dp))
        Text("Active Admin Passkeys:")
        when (isFetchingPasskeys){
            true->{
                Text("Loading passkeys...")
            }
            false->{
                ActivePasskeysList.forEach {
                    Text(it)
                }
            }
        }
    }
}
