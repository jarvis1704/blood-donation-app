package com.example.blooddonationapp.home.interfaces

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.blooddonationapp.R
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.global.data.updateCurrentUser
import com.example.blooddonationapp.home.data.homeViewmodel


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun userProfile() {
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
                        start = 16.dp
                    )
                ) {
                    Text("Donor Details", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.85f)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                        .padding(horizontal = dimensionResource(id = R.dimen.profile_horizontal_padding)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when(currentUser.profilePic) {
                        "" -> {
                            Image(
                                painterResource(id = R.drawable.default_user_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(min(200.dp, dimensionResource(id = R.dimen.profile_pic_size)))
                                    .clip(CircleShape))
                        }
                        else -> {
                            Image(
                                painter = rememberAsyncImagePainter(model = currentUser.profilePic),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(min(200.dp, dimensionResource(id = R.dimen.profile_pic_size)))
                                    .clip(CircleShape))
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = currentUser.username,
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(16.dp))
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = Color(0x988BC34A),
                        contentColor = Color.White,
                        modifier = Modifier
                            .height(34.dp)
                            .fillMaxWidth(0.8f),
                        shadowElevation = 8.dp,
                        tonalElevation = 8.dp
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(painter = painterResource(R.drawable.verified_icon), contentDescription = "verified logo")
                            Text("Can Donate Blood", Modifier.padding(horizontal = 8.dp), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = dimensionResource(id = R.dimen.card_horizontal_padding)),
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.elevatedCardElevation(8.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp, vertical = 24.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                InfoColumn(label = "Blood Group", value = currentUser.bloodGroup)
                                InfoColumn(label = "Donated", value = "2")
                                InfoColumn(label = "Blood Group", value = "0")
                            }
                            Spacer(Modifier.height(16.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.LocationOn,
                                    contentDescription = "Location Icon",
                                    tint = Color.Black
                                )
                                Text(
                                    "Tezpur University",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            MenuRow(
                                icon = Icons.Default.Settings,
                                text = "SETTINGS & PREFERENCES",
                                onClick = {}
                            )
                            Divider(
                                modifier = Modifier.padding(vertical = 12.dp),
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                            )
                            MenuRow(
                                icon = Icons.Default.Notifications,
                                text = "NOTIFICATIONS",
                                onClick = {}
                            )
                            Divider(
                                modifier = Modifier.padding(vertical = 12.dp),
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                            )
                            MenuRow(
                                icon = Icons.Default.Info,
                                text = "HELP & FAQS",
                                onClick = {}
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoColumn(
    label: String,
    value: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            value,
            fontSize = 32.sp,
            color = Color(0xFFEB4335),
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Text(
            label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
private fun MenuRow(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}