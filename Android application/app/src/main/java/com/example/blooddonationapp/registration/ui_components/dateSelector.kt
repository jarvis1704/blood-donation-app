package com.example.blooddonationapp.registration.ui_components

import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun dateYearSelector(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
){
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

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = isCurrentMonth) { onDateSelected(date) }
                ){
                    Text(text = date.dayOfMonth.toString())
                }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    Column {
        dateYearSelector(
            selectedDate = selectedDate,
            onDateSelected = { selectedDate = it }
        )
    }
}