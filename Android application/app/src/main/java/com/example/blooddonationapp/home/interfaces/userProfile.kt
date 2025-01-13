package com.example.blooddonationapp.home.interfaces

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.blooddonationapp.R
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.global.data.updateCurrentUser
import com.example.blooddonationapp.home.data.homeViewmodel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun userProfile(){
    updateCurrentUser()

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f)
                    .background(Color(0xFFEB4335))
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 98.dp, start = 16.dp)
                ) {
                    Text("Donor Details")
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.85f)
                    .background(Color.White)
            ){
                Column(
                    modifier = Modifier.fillMaxSize().padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when(currentUser.profilePic){
                        ""->{
                            Image(
                                painterResource(id = R.drawable.default_user_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                                    .clip(CircleShape))
                        }
                        else->{
                            Image(
                                painter = rememberAsyncImagePainter(model = currentUser.profilePic),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                                    .clip(CircleShape))
                        }
                    }
                    Text(text = currentUser.username)
                    Surface(
                        shape = RectangleShape.let {
                            RoundedCornerShape(16.dp)
                        },
                        color = Color(0x988BC34A),
                        contentColor = Color.White,
                        modifier = Modifier.height(34.dp).width(300.dp),
                        shadowElevation = 8.dp,
                        tonalElevation = 8.dp
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(painter = painterResource(R.drawable.verified_icon), contentDescription = "verified logo")
                            Text("Can Donate Blood", Modifier.padding(horizontal = 8.dp))
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    Card(modifier = Modifier
                        .fillMaxWidth(0.8f),
                        onClick = { /*TODO*/ }) {
//                        Column(modifier = Modifier
//                            .fillMaxWidth(),
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            Text(text = "Blood Group:" + currentUser.bloodGroup)
//                            Text(text = currentUser.area)
//                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(currentUser.bloodGroup)
                                    Text("Blood Group")
                                }
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                )  {
                                    //donated count
                                    Text("2")
                                    Text("Donated")
                                }
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                )  {
                                    //Requested count
                                    Text("0")
                                    Text("Blood Group")
                                }
                            }
                            Spacer(Modifier.height(16.dp))
                            Row {
                                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "Location Icon", tint = Color.Black)
                                Text("Tezpur University")
                            }
                        }

                    }
                }
            }
        }


    }
}