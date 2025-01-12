package com.example.blooddonationapp.registration.ui_components

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.blooddonationapp.registration.data.tempRegistrationDetails
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.registration.data.ProcessImage
import com.example.blooddonationapp.registration.data.photoUploadStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("NewApi")
@Composable
fun imagePicker() {
    val context = LocalContext.current

    var isImagePicked by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        isImagePicked = true
        tempRegistrationDetails.aadharPhotoUri = uri
    }

    Button(
        onClick = { launcher.launch("image/*") }
    ) {
        Text(text = if (isImagePicked) tempRegistrationDetails.aadharPhotoUri?.path.toString() else "Choose from Gallery")
    }
}
//            tempRegistrationDetails.aadharPhotoUri?.let { uri ->
//            Image(
//                painter = rememberAsyncImagePainter(
//                    ImageRequest.Builder(context)
//                        .data(data = uri)
//                        .build()
//                ),
//                contentDescription = "Selected image",
//                modifier = Modifier
//                    .size(200.dp)
//            )

