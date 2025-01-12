package com.example.blooddonationapp.registration.data

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.net.Uri
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.blooddonationapp.global.data.errorMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.ByteArrayOutputStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ImageProcessor(private val context: Context){
    private val db = Firebase.firestore

    suspend fun processAndUploadGoogleIcon(imageUri: Uri): String = withContext(Dispatchers.IO) {
        try {
            // Load image from the provided URI
            val inputStream = context.contentResolver.openInputStream(imageUri) // Use Uri to get InputStream
            val bitmap = BitmapFactory.decodeStream(inputStream) // Decode bitmap from InputStream
            inputStream?.close() // Close InputStream after use

            // Resize if needed
            val MAX_WIDTH = 400f
            var resizedBitmap = bitmap
            if (bitmap.width > MAX_WIDTH) {
                val ratio = MAX_WIDTH / bitmap.width
                val newWidth = MAX_WIDTH.toInt()
                val newHeight = (bitmap.height * ratio).toInt()
                resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
            }

            // Convert to B&W
            val bwBitmap = convertToBlackAndWhite(resizedBitmap)

            // Convert to Base64
            val base64String = convertBitmapToBase64(bwBitmap)

            // Check size
            val approximateBytes = base64String.length * 0.75
            if (approximateBytes > 1_000_000) {
                errorMessage = "Image too large after processing"
            }

            // Upload to Firestore
            val documentData = hashMapOf(
                "imageData" to base64String,
                "timestamp" to com.google.firebase.Timestamp.now(),
                "useremail" to (FirebaseAuth.getInstance().currentUser?.email ?: "null")
            )

            db.collection("aadhardetails").document(FirebaseAuth.getInstance().currentUser!!.uid)
                .set(documentData, SetOptions.merge())
                .addOnSuccessListener {
                    photoUploadStatus = "Uploaded Successfully"
                }.addOnFailureListener {
                    errorMessage = it.message.toString()
                    photoUploadStatus = "Upload failed, try again"
                }

            return@withContext base64String
        } catch (e: Exception) {
            throw e
        }
    }

    private fun convertToBlackAndWhite(original: Bitmap): Bitmap {
        val bwBitmap = Bitmap.createBitmap(
            original.width,
            original.height,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bwBitmap)
        val paint = Paint()
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f)
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(original, 0f, 0f, paint)

        return bwBitmap
    }

    private fun convertBitmapToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    suspend fun retrieveProcessedIcon(): String? = withContext(Dispatchers.IO) {
        try {
            val snapshot = db.collection("aadhardetails")
                .whereEqualTo("imageName", "google_icon")
                .orderBy("timestamp")
                .limitToLast(1)
                .get()
                .await()

            return@withContext if (!snapshot.isEmpty) {
                snapshot.documents[0].getString("imageData")
            } else null
        } catch (e: Exception) {
            null
        }
    }
}

// Composable to display the image
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProcessImage(context: Context, imageUri: Uri) {
    val imageProcessor = remember { ImageProcessor(context) }
    val scope = rememberCoroutineScope()

    scope.launch {
        try {
            val result = imageProcessor.processAndUploadGoogleIcon(imageUri)
        } catch (e: Exception) {
            errorMessage = e.message.toString()
        }
    }
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Button(
//            onClick = {
//
//            }
//        ) {
//            Text("Process and Upload Image")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//        imageBase64?.let { base64String ->
//            val bitmap = remember(base64String) {
//                val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
//                BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
//            }
//
//            Image(
//                bitmap = bitmap.asImageBitmap(),
//                contentDescription = "Processed Google Icon",
//                modifier = Modifier
//                    .size(200.dp)
//                    .clip(RoundedCornerShape(8.dp))
//            )
//        }

}