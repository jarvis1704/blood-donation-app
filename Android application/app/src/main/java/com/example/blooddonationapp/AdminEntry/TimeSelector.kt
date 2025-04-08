package com.example.blooddonationapp.AdminEntry

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("DiscouragedPrivateApi", "UnrememberedMutableState")
@Composable
fun TimeSelector(
    initialHour: Int = 12,
    initialMinute: Int = 0,
    toUpdate: MutableState<LocalTime?>
) {
    val context = LocalContext.current

    // Initialize state with toUpdate if available, otherwise use initial values
    val selectedHour = remember {
        mutableStateOf(toUpdate.value?.hour ?: initialHour)
    }
    val selectedMinute = remember {
        mutableStateOf(toUpdate.value?.minute ?: initialMinute)
    }

    // Update the selected time when toUpdate changes
    LaunchedEffect(toUpdate.value) {
        toUpdate.value?.let {
            selectedHour.value = it.hour
            selectedMinute.value = it.minute
        }
    }

    // TimePickerDialog
    if (showDialog) {
        TimePickerDialog(
            context,
            { _, hour, minute ->
                selectedHour.value = hour
                selectedMinute.value = minute
                toUpdate.value = LocalTime.of(hour, minute)
                showDialog = false
            },
            selectedHour.value,
            selectedMinute.value,
            false
        ).apply {
            setOnCancelListener {
                showDialog = false
            }
        }.show()
    }
}