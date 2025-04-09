package com.example.blooddonationapp.AdminEntry

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.global.data.NewGlobalAlert
import com.example.blooddonationapp.home.data.HomeViewModel
import com.example.blooddonationapp.home.data.bloodRequest
import com.example.blooddonationapp.home.data.globalBloodRequestList
import com.example.blooddonationapp.home.interfaces.BloodRequestAnouncementCard

@Composable
fun ActiveBloodRequests(
    homeViewModel: HomeViewModel = hiltViewModel()
){
    LaunchedEffect(Unit) {
        homeViewModel.FetchBloodRequests()
    }
    Column (
        modifier = Modifier.padding(16.dp)
    ){
        Spacer(Modifier.height(16.dp))
        Text("Active Blood Requests:")
        globalBloodRequestList?.forEach { req->
            BloodRequestEditComposable(req)
        }
    }
}


@SuppressLint("NewApi")
@Composable
fun BloodRequestEditComposable(
    bloodRequest: bloodRequest,
    adminViewmodel: AdminViewmodel = hiltViewModel()
) {
    Card(
        modifier= Modifier.defaultMinSize(minWidth = 393.dp, minHeight = 180.dp),
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
            Text("EMERGENCY ${bloodRequest.bloodtype} BLOOD NEEDED", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White, lineHeight = 1.25.em)
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "Location Icon", tint = Color.White)
                Text(bloodRequest.hospital, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
            Spacer(Modifier.height(8.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    "${bloodRequest.date?.dayOfMonth} ${bloodRequest.date?.month}",
                    fontSize = 16.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.clip(RoundedCornerShape(8.dp))
                        .background(
                            Color.Black).padding(8.dp), color = Color.White)
                IconButton(
                    onClick = {
                        NewGlobalAlert(
                            title = "Delete Blood Request",
                            details = "Are you sure you want to delete the request? This cannot be undone.",
                            onCancelClick = {},
                            onConfirmClick = {
                                adminViewmodel.DeleteBloodRequest(bloodRequest)
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
    Spacer(Modifier.height(18.dp))
}