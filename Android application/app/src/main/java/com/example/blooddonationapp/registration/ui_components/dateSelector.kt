package com.example.blooddonationapp.registration.ui_components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blooddonationapp.registration.data.tempRegistrationDetails
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun dateYearSelector(
    dateToBeUpdated: MutableState<LocalDate>,
    selectedDate: LocalDate) {
    var displayedMonth by remember { mutableStateOf(YearMonth.from(selectedDate)) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Month and Year Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Month Selector
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { displayedMonth = displayedMonth.minusMonths(1) }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Previous Month",
                            tint = Color(0xFFEB4335)
                        )
                    }
                    Text(
                        text = displayedMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    IconButton(onClick = { displayedMonth = displayedMonth.plusMonths(1) }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Next Month",
                            tint = Color(0xFFEB4335)
                        )
                    }
                }

                // Year Selector
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { displayedMonth = displayedMonth.minusYears(1) }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Previous Year",
                            tint = Color(0xFFEB4335)
                        )
                    }
                    Text(
                        text = "${displayedMonth.year}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    IconButton(onClick = { displayedMonth = displayedMonth.plusYears(1) }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Next Year",
                            tint = Color(0xFFEB4335)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Week Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                DayOfWeek.values().forEach { dayOfWeek ->
                    Text(
                        text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            val firstDayOfMonth = displayedMonth.atDay(1)
            val firstDayOfGrid = firstDayOfMonth.minusDays(firstDayOfMonth.dayOfWeek.value.toLong() - 1)

            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier.height(280.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items(42) { index ->
                    val date = firstDayOfGrid.plusDays(index.toLong())
                    val isCurrentMonth = date.month == displayedMonth.month
                    val isSelected = date == dateToBeUpdated.value

                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isSelected -> Color(0xFFEB4335)
                                    isCurrentMonth -> Color.Transparent
                                    else -> Color.Transparent
                                }
                            )
                            .clickable(enabled = isCurrentMonth) {
                                dateToBeUpdated.value = date
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            color = when {
                                isSelected -> Color.White
                                isCurrentMonth -> Color.Black
                                else -> Color.LightGray
                            }
                        )
                    }
                }
            }
        }
    }
    Text(text = "Selected Date: ${dateToBeUpdated.value.dayOfMonth} ${dateToBeUpdated.value.month}, ${dateToBeUpdated.value.year}")
}
