package com.example.blooddonationapp.AdminEntry.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.example.blooddonationapp.AdminEntry.data.ActivePasskeysList
import com.example.blooddonationapp.AdminEntry.data.AdminViewmodel
import com.example.blooddonationapp.AdminEntry.data.isFetchingPasskeys
import com.example.blooddonationapp.AdminEntry.data.isNewPasskeyDialogue
import com.example.blooddonationapp.AdminEntry.data.tempNewPasskey
import com.example.blooddonationapp.global.data.NewGlobalAlert


@Composable
fun AdminPasskeys(
    adminViewmodel: AdminViewmodel = hiltViewModel()
){
    NewPasskey()
    LaunchedEffect(Unit) {
        isFetchingPasskeys = true
        adminViewmodel.GetActivePasskeys()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header section with red background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .background(Color(0xFFEB4335))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(30.dp))
                    Text(
                        "Admin Passkeys",
                        fontSize = 32.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Manage Admin Access Credentials",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Main content with white background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
                    .background(Color.White)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                        .align(Alignment.TopCenter),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Active Admin Passkeys",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFEB4335)
                            )
                            IconButton(
                                onClick = {
                                    isNewPasskeyDialogue = true
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add key",
                                    tint = Color(0xFFEB4335)
                                )
                            }
                        }

                        Spacer(Modifier.height(8.dp))
                        Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)
                        Spacer(Modifier.height(16.dp))

                        when (isFetchingPasskeys) {
                            true -> {
                                Text(
                                    "Loading passkeys...",
                                    fontSize = 16.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(vertical = 16.dp)
                                )
                            }
                            false -> {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFFF5F5F5),
                                        contentColor = Color.Black
                                    ),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        ActivePasskeysList.forEach {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(12.dp),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    it.key,
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Medium
                                                )
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
                                                    Icon(
                                                        imageVector = Icons.Default.Delete,
                                                        contentDescription = "Delete request",
                                                        tint = Color(0xFFEB4335)
                                                    )
                                                }
                                            }
                                            // Add a divider between items except for the last one
                                            if (it != ActivePasskeysList.last()) {
                                                Divider(
                                                    color = Color(0xFFE0E0E0),
                                                    thickness = 1.dp,
                                                    modifier = Modifier.padding(horizontal = 12.dp)
                                                )
                                            }
                                        }
                                    }
                                }
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
                Column {
                    Text(
                        "Enter Passkey",
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    TextField(
                        value = tempNewPasskey,
                        onValueChange = {
                            tempNewPasskey = it
                        },
                        placeholder = { Text("Enter secure passkey") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color(0xFFEB4335),
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            disabledPlaceholderColor = Color.LightGray
                        ),
                        singleLine = true
                    )
                }
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
                            .weight(1f)
                            .height(48.dp)
                            .padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    }
                    Button(
                        onClick = {
                            adminViewmodel.AddPasskey(tempNewPasskey)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .padding(start = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEB4335)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Confirm",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
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