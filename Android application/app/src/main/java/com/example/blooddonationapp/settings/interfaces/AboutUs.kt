package com.example.blooddonationapp.settings.interfaces

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.blooddonationapp.R
import com.example.blooddonationapp.auth.data.EmailLoginViewModel
import com.example.blooddonationapp.global.data.updateCurrentUser


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AboutUs(
    goto_userProfile:()-> Unit
) {
    updateCurrentUser()

    val primaryRed = Color(0xFFEB4335)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize()
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f)
                    .background(primaryRed)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(
                        top = dimensionResource(id = R.dimen.profile_top_padding),
                        start = 16.dp
                    )
                ) {
                    IconButton({
                        goto_userProfile()
                    }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back Button", tint = Color.White)
                    }
                    Text("About Us", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }

            // Content
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.85f)
                    .background(primaryRed)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                        .background(primaryRed)
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = dimensionResource(id = R.dimen.profile_horizontal_padding)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    ) {
                        Column(
                            Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Who We Are
                            SectionTitle("Who We Are")
                            Text(
                                text = "Tezpur Red Cross Society is a dedicated non-profit organization working tirelessly to ensure that no one has to wait for life-saving blood.",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Through compassion, commitment, and community spirit, we connect voluntary donors with patients in urgent need across Tezpur and nearby regions.",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Our mission is simple but powerful — to make blood donation accessible, organized, and immediate for everyone who needs it.",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            SectionDivider()

                            // Our App
                            SectionTitle("Our App")
                            Text(
                                text = "In an effort to modernize and expand our reach, we developed the [App Name] — a smart, easy-to-use platform where donors and patients can connect seamlessly.",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            Text(
                                text = "Through this app, users can:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            BulletPoint("Find nearby blood donors or donation camps")
                            BulletPoint("Request urgent blood donations")
                            BulletPoint("Stay updated with donation drives organized by the Tezpur Red Cross Society")

                            Text(
                                text = "Our goal is to make blood donation faster, safer, and part of everyday life.",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 12.dp)
                            )

                            SectionDivider()

                            // Development Team
                            SectionTitle("Development Team")
                            Text(
                                text = "The [App Name] was lovingly built by a group of passionate developers from Tezpur:",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            TeamMember("Prudam Priyosanga Dutta", "App Logic and Backend Development")
                            TeamMember("Biprangshu Das", "UI/UX Design")
                            TeamMember("Chayan Sarkar", "Planning and Testing")
                            TeamMember("Sujal Kumar", "Guidance and Supervision")

                            Text(
                                text = "We worked closely with the Tezpur Red Cross Society to ensure the app truly meets the real-world needs of patients, hospitals, and donors.",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 12.dp)
                            )

                            SectionDivider()

                            // Special Thanks
                            SectionTitle("Special Thanks")
                            Text(
                                text = "We are deeply grateful to Dr. Rupam Goswami for his mentorship, guidance, and for connecting us with the Tezpur Red Cross Society.",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "His support made this project possible.",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            SectionDivider()

                            // How You Can Help
                            SectionTitle("How You Can Help")

                            HelpPoint("Spread the Word", "Share the app with friends, family, and your community.")
                            HelpPoint("Follow Us", "Stay updated through our social media channels.\n(Links coming soon!)")
                            HelpPoint("Send Us Feedback", "We're always looking to improve.\nFeel free to email us at [yourfeedbackemail@example.com] with your suggestions or ideas!")

                            Text(
                                text = "Together, every small step brings us closer to saving more lives.",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 12.dp, bottom = 16.dp)
                            )

                            SectionDivider()

                            // Thank You
                            SectionTitle("Thank You")
                            Text(
                                text = "Your support keeps this mission alive.",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Every donor, every share, and every act of kindness brings hope to someone in need.",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Together, we are stronger. ❤",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                color = primaryRed,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }

                    // Add some space at the bottom for better scrolling experience
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFEB4335),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
private fun SectionDivider() {
    Divider(
        color = Color(0xFFEEEEEE),
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    )
}

@Composable
private fun BulletPoint(text: String) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp, start = 24.dp, end = 16.dp)
    ) {
        Text(
            text = "•",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
private fun TeamMember(name: String, role: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Text(
            text = name,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "— $role",
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}

@Composable
private fun HelpPoint(title: String, description: String) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = title,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = description,
            fontSize = 15.sp,
            lineHeight = 20.sp
        )
    }
}