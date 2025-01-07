package com.example.blooddonationapp.registration.ui_components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun dateYearSelector(
    selectedDate: LocalDate
){
    var userSelectedDate by remember { mutableStateOf(LocalDate.now()) }

    var displayedMonth by remember { mutableStateOf(YearMonth.from(selectedDate)) }
    Card(
        modifier = Modifier.fillMaxWidth(0.9f)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row (
                modifier = Modifier.fillMaxWidth()
            ){

                //change month
                IconButton(
                    onClick = {
                        displayedMonth = displayedMonth.minusMonths(1)
                    }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Previous")
                }
                Text(text = displayedMonth.month.name)
                IconButton(
                    onClick = {
                        displayedMonth = displayedMonth.plusMonths(1)
                    }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Next")
                }

                //change year
                IconButton(
                    onClick = {
                        displayedMonth = displayedMonth.minusYears(1)
                    }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Previous")
                }
                Text(text = "${displayedMonth.year}")
                IconButton(
                    onClick = {
                        displayedMonth = displayedMonth.plusYears(1)
                    }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Next")
                }
            }

            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                DayOfWeek.values().forEach { dayOfWeek ->
                    Text(
                        text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                        modifier = Modifier.weight(1f))
                }
            }
        }

        val firstDayOfMonth = displayedMonth.atDay(1)
        val firstDayOfGrid = firstDayOfMonth.minusDays(firstDayOfMonth.dayOfWeek.value.toLong() - 1)

        LazyVerticalGrid(columns = GridCells.Fixed(7)) {
            items(42){ index ->
                val date = firstDayOfGrid.plusDays(index.toLong())
                val isCurrentMonth = date.month == displayedMonth.month

                //this box is a day
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(color = if (date == userSelectedDate) Color.Blue else Color.Unspecified)
                    .clickable(enabled = isCurrentMonth) {
                        userSelectedDate = date
                    }
                ){
                    Text(text = date.dayOfMonth.toString())
                }
            }
        }
    }
    Text(text = "Selected Date: ${userSelectedDate.dayOfMonth} ${userSelectedDate.month}, ${userSelectedDate.year}")
}
