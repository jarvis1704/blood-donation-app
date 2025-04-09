package com.example.blooddonationapp.AdminEntry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.global.data.NewGlobalAlert
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.global.data.isErrorDialogue


@Composable
fun AdminPasskeys(
    adminViewmodel: AdminViewmodel = hiltViewModel()
){
    NewPasskey()
    LaunchedEffect(Unit) {
        isFetchingPasskeys = true
        adminViewmodel.GetActivePasskeys()
    }
    Column (
        modifier = Modifier.padding(16.dp)
    ){
        Spacer(Modifier.height(32.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Active Admin Passkeys:")
            IconButton(
                onClick = {
                    isNewPasskeyDialogue = true
                }
            ) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "Add key")
            }
        }
        Spacer(Modifier.height(16.dp))
        when (isFetchingPasskeys){
            true->{
                Text("Loading passkeys...")
            }
            false->{
                Card (

                ){
                    ActivePasskeysList.forEach {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(it.key)
                            IconButton(
                                onClick = {
                                    NewGlobalAlert(
                                        title = "Delete Admin Passkey",
                                        details = "Are you sure you want to delete the passkey? This cannot be undone.",
                                        onCancelClick = {},
                                        onConfirmClick = {
                                            adminViewmodel.DeletePasskey(it)
                                        }
                                    )
                                }
                            ) {
                                Icon(imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete request")
                            }
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun NewPasskey(
    adminViewmodel: AdminViewmodel = hiltViewModel()
) {
    if (isNewPasskeyDialogue) {
        AlertDialog(
            onDismissRequest = { isNewPasskeyDialogue = false },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "New Passkey",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFEB4335)
                    )
                }
            },
            text = {
                TextField(
                    value = tempNewPasskey,
                    onValueChange = {
                        tempNewPasskey = it
                    },
                    modifier = Modifier.padding(16.dp)
                )
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            isNewPasskeyDialogue = false
                        },
                        modifier = Modifier
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEB4335)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Button(
                        onClick = {
                            adminViewmodel.AddPasskey(tempNewPasskey)
                        },
                        modifier = Modifier
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEB4335)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Confirm",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(16.dp),
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }
}
