package com.example.blooddonationapp.AdminEntry

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import android.util.Base64
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun AadharVerificationPage(
    AdminViewmodel:AdminViewmodel = hiltViewModel()
){
    AdminViewmodel.getPendingAadhar()
    Column(modifier = Modifier.fillMaxSize().padding(top = 100.dp).padding(20.dp)) {
        Text("Verify pending aadhar details here:")
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            aadharPendingList?.let {
                items(it.toList()){ item ->
                    showAadharUser(item)
                }
            }
        }
    }
}


@Composable
fun showAadharUser(user:aadharUser){
    Column(
        modifier = Modifier.fillMaxWidth(0.95f).border(2.dp, Color.Red),
    ) {
        Text(user.useremail)
        Text(user.aadharNo.toString())
        Text(user.aadharDOB.toString())
        Text(user.aadharStatus)
        DisplayImageFromBase64(user.aadharPhotoString)
    }
}

fun getBitmapFromBase64(base64String: String): Bitmap? {
    return try {
        val decodedString = Base64.decode(base64String, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Composable
fun DisplayImageFromBase64(base64String: String) {
    val bitmap = getBitmapFromBase64(base64String)
    bitmap?.let {
        Image(bitmap = it.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.size(400.dp))
    }
}