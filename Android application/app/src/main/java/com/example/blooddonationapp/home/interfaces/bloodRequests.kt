package com.example.blooddonationapp.home.interfaces

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.global.data.updateCurrentUser
import com.example.blooddonationapp.home.data.HomeViewModel
import com.example.blooddonationapp.home.data.announcement
import com.example.blooddonationapp.home.data.bloodRequest
import com.example.blooddonationapp.home.data.globalBloodRequestList
import com.example.blooddonationapp.ui.theme.BloodDonationAppColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun bloodRequests(
    goto_homepage:()->Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
){
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
                        goto_homepage()
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
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 24.dp, start = 14.dp, end = 14.dp)
                        .navigationBarsPadding(),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = "BLOOD REQUESTS NEAR YOU", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(3.dp))
                    if (currentUser.bloodGroup.isNotEmpty()){
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Filter By Your Blood Group: ${currentUser.bloodGroup}", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
                            Switch(
                                checked = currentUser.isBloodTypeFilter,
                                onCheckedChange = {
                                    currentUser.isBloodTypeFilter = !currentUser.isBloodTypeFilter
                                },
                                colors = SwitchDefaults.colors(
                                    checkedTrackColor = BloodDonationAppColor.BloodRed,

                                    )
                            )
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if (globalBloodRequestList?.isEmpty() == true){
                            item {
                                Text("No Active Blood Requests at the moment!!")
                            }
                        }
                        globalBloodRequestList?.let {
                            items(it.toList()){item->
                                if (currentUser.isBloodTypeFilter){
                                    if (item.bloodtype == currentUser.bloodGroup){
                                        BloodRequestAnouncementCard(item)
                                    }
                                }else{
                                    BloodRequestAnouncementCard(item)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun BloodRequestAnouncementCard(bloodRequest: bloodRequest) {
    Card(
        modifier= Modifier.defaultMinSize(minWidth = 393.dp, minHeight = 180.dp),
        colors = CardDefaults.cardColors(
            Color(0xFFEB4335)
        ),
        elevation = CardDefaults.elevatedCardElevation(
            8.dp
        ),
        onClick = {/*todo AnnouncementCard implementation*/}
    ) {
        Column(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Text("EMERGENCY ${bloodRequest.bloodtype} BLOOD NEEDED", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White, lineHeight = 1.25.em)
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "Location Icon", tint = Color.White)
                Text(bloodRequest.hospital, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
            Spacer(Modifier.height(8.dp))
            Text(
                "${bloodRequest.date?.dayOfMonth} ${bloodRequest.date?.month}",
                fontSize = 16.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.clip(RoundedCornerShape(8.dp))
                    .background(
                        Color.Black).padding(8.dp), color = Color.White) }
    }
    Spacer(Modifier.height(18.dp))
}