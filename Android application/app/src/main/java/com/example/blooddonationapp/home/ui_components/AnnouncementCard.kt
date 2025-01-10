package com.example.blooddonationapp.home.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnnouncementCard(modifier: Modifier = Modifier) {
    Card(
        modifier= Modifier.height(187.dp).width(393.dp),
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
            Text("BLOOD DONATION CAMP", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "Location Icon", tint = Color.White)
                Text("Tezpur University", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
            }
            Spacer(Modifier.height(8.dp))
            Text("31 JULY", fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(
                Color.Black).padding(8.dp), color = Color.White)
            Spacer(Modifier.height(8.dp))
            Text("10:00AM Onwards", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview
@Composable
private fun AnnouncementCardPreview() {
    AnnouncementCard()
}