package com.example.blooddonationapp.settings.interfaces

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.R
import com.example.blooddonationapp.auth.data.EmailLoginViewModel
import com.example.blooddonationapp.global.data.updateCurrentUser


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SettingsAndPreferences(
    goto_loadingpage:()->Unit,
    emailLoginViewModel: EmailLoginViewModel = hiltViewModel(),
    goto_userProfile:()-> Unit
) {
    updateCurrentUser()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f)
                    .background(Color(0xFFEB4335))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.profile_top_padding),
                        start = 8.dp
                    )
                ) {
                    IconButton({
                        goto_userProfile()
                    }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back Button", tint = Color.White)
                    }
                    Text("Settings and Preferences", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.85f)
                    .background(Color(0xFFEB4335))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                        .background(Color.White)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = dimensionResource(id = R.dimen.profile_horizontal_padding)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("this is settings page")
                }
            }
        }
    }
}