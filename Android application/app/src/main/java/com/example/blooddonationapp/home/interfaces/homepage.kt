package com.example.blooddonationapp.home.interfaces

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.R
import com.example.blooddonationapp.auth.data.EmailLoginViewModel
import com.example.blooddonationapp.global.data.currentUser
import com.example.blooddonationapp.global.data.updateCurrentUser
import com.example.blooddonationapp.home.data.globalAnnouncementList
import com.example.blooddonationapp.home.ui_components.AnnouncementCard

@Composable
fun homepage(
    goto_registration:()-> Unit,
    goto_aadharregistration:()-> Unit,
    goto_bloodreqform:()->Unit,
    viewModel: EmailLoginViewModel = hiltViewModel(),
    goto_donorform: ()-> Unit
){
//    var viewmodel: emailLoginViewmodel = viewModel()
    updateCurrentUser()
    Box(modifier = Modifier.fillMaxSize()){

        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .background(Color(0xFFEB4335))

            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text("Welcome", fontSize = 32.sp, fontWeight = FontWeight.Medium, color = Color.White)
                            Spacer(Modifier.height(8.dp))
                            //replace it with user name variable
                            Text(currentUser.username, fontSize = 40.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        }
//                        IconButton({/*todo Notification inplementation*/}) {
//                            Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Notification Icon", tint = Color.White)
//                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
                    .background(Color.White)
            ){
                Column(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp).navigationBarsPadding()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().offset(y = (-50).dp),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        Card(
                            modifier = Modifier.weight(1f).aspectRatio(1.6f),
                            elevation = CardDefaults.cardElevation(
                                8.dp
                            ),
                            colors = CardDefaults.cardColors(
                                Color.White
                            ),
                            onClick = {goto_donorform()}
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                            ) {
                                Image(painter = painterResource(R.drawable.home_page_logo1), contentDescription = "ButtonLogo", modifier = Modifier.height(50.dp).width(25.dp))
                                Spacer(Modifier.height(8.dp))
                                Text("DONATE")
                            }
                        }
                        Card(
                            modifier = Modifier.weight(1f).aspectRatio(1.6f),
                            elevation = CardDefaults.cardElevation(
                                8.dp
                            ),
                            colors = CardDefaults.cardColors(
                                Color.White
                            ),
                            onClick = {
                                goto_bloodreqform()
                            }
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                            ) {
                                Image(painter = painterResource(R.drawable.home_page_logo2), contentDescription = "ButtonLogo", modifier = Modifier.height(50.dp).width(30.dp))
                                Spacer(Modifier.height(8.dp))
                                Text("Find Donor")
                            }
                        }
                    }
                    Text("See What's new!", fontSize = 32.sp, modifier = Modifier.padding(vertical = 16.dp), fontWeight = FontWeight.Bold)
                    //vertical scrollable column
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            when{
                                currentUser.registrationType != "registered"->{
                                    //registration reminder
                                    Card (
                                        modifier = Modifier.height(50.dp)
                                            .clickable(
                                                onClick = {goto_registration()}
                                            )
                                    ){
                                        Text("Complete your registration details ->")
                                    }
                                }
                                currentUser.aadharStatus !in "submitted verified" && currentUser.registrationType == "registered"->{
                                    //aadhar reminder
                                    Card (
                                        modifier = Modifier.height(50.dp)
                                            .clickable(
                                                onClick = {goto_aadharregistration()}
                                            )
                                    ){
                                        Text("Complete your aadhar details ->")
                                    }
                                }
                                else->{

                                }
                            }
                        }
                        globalAnnouncementList?.let {
                            items(it.toList()){ item ->
                                AnnouncementCard(item)
                            }
                            item {
                                Spacer(modifier = Modifier.fillMaxWidth().padding(25.dp))
                            }
                        }
                    }
                }
            }
        }

//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Text(text = "this is homepage\n\n\nyou are logged in")
//            Button(onClick = {
//                viewmodel.signout(goto_loadingpage)
//            }) {
//                Text(text = "logout")
//            }
//        }
    }
}

