package com.example.blooddonationapp.home.interfaces

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.global.data.updateCurrentUser
import com.example.blooddonationapp.home.data.announcement
import com.example.blooddonationapp.ui.theme.BloodDonationAppColor
import java.time.format.DateTimeFormatter

@Composable
fun bloodRequests(){
    updateCurrentUser()
    Box(modifier = Modifier.fillMaxSize()){
        Column {
            Box(
                modifier = Modifier.fillMaxWidth().weight(0.15f).background(BloodDonationAppColor.BloodRed)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 84.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton({

                    }) {
                        Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Back Button", tint = Color.White, modifier = Modifier.size(30.dp))
                    }
                    Text("Blood Requests", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

            }
            Box(
                modifier = Modifier.fillMaxWidth().weight(0.85f)
            ){
                Column(
                    modifier = Modifier.fillMaxSize().padding(top = 24.dp, start = 24.dp, end = 24.dp).navigationBarsPadding().verticalScroll(
                        rememberScrollState()
                    ),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = "BLOOD REQUESTS NEAR YOU", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(16.dp))
//                    Text(currentUser.bloodGroup+" Type Blood Required Near You")
                    repeat(5){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ){
                            repeat(5){
                                BloodRequestAnouncementCard()
                            }
                        }
                    }
//                    Spacer(modifier = Modifier.padding(.dp))
                    Text("All Blood Requests")


                }
            }

        }

    }
}

@SuppressLint("NewApi")
@Composable
fun BloodRequestAnouncementCard() {
//    val formatter = DateTimeFormatter.ofPattern("hh:mm a")
//    val formattedTime = announcement.dateAndTime?.format(formatter)
    Card(
        modifier= Modifier.defaultMinSize(minWidth = 393.dp, minHeight = 200.dp),
        colors = CardDefaults.cardColors(
            Color(0xFFEB4335)
        ),
        elevation = CardDefaults.elevatedCardElevation(
            8.dp
        ),
        onClick = {/*todo AnnounmentCard implementation*/}
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Text("EMERGENCY BLOOD NEEDED", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White, lineHeight = 1.25.em)
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "Location Icon", tint = Color.White)
                Text("TEZPUR MENTAL HOSPITAL", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
            Spacer(Modifier.height(8.dp))
            Text("28 Nov",
                fontSize = 16.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.clip(RoundedCornerShape(8.dp))
                    .background(
                        Color.Black).padding(8.dp), color = Color.White)
            Spacer(Modifier.height(8.dp))

            Text("10:00AM Onwards",
                fontSize = 16.sp, fontWeight = FontWeight.SemiBold
            )
        }
    }
}