package com.example.blooddonationapp.registration.data

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.home.data.TimestampToLocalDate
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(): ViewModel(){
    private val _auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val _db : FirebaseFirestore = FirebaseFirestore.getInstance()

    fun saveRegistrationType(type:String, goto_homepage:()->Unit){
        val datamap = mapOf("registration_type" to type)
        if (_auth.currentUser != null){
            _db.collection("userdetails").document(_auth.currentUser!!.uid)
                .set(datamap, SetOptions.merge())
                .addOnSuccessListener {
                    goto_homepage()
                }.addOnFailureListener {
                    errorMessage = it.message.toString()
                }
        }else{
            errorMessage = "Error: No logged in user found"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun saveBirthdate(goto_nextpage:()->Unit={}){
        val timestamp = Timestamp(Date.from(tempRegistrationDetails.birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
        val date = mapOf(
            "birthdate" to timestamp
        )
        if (_auth.currentUser != null){
            //first check if birthdate already exists
            try {
                val document = _auth.currentUser?.let { _db.collection("userdetails").document(it.uid) }
                    ?.get()?.await()
                if (document != null){
                    val previousBirthDate = document.getDate("birthdate")
                    if (previousBirthDate != null){
                        //update the existing birthdate
                        _db.collection("userdetails").document(_auth.currentUser!!.uid)
                            .update("birthdate", timestamp)
                            .addOnSuccessListener {
                                goto_nextpage()
                            }.addOnFailureListener {
                                errorMessage = it.message.toString()
                            }
                    }
                    else{
                        //add new birthdate
                        _db.collection("userdetails").document(_auth.currentUser!!.uid)
                            .set(date, SetOptions.merge())
                            .addOnSuccessListener {
                                goto_nextpage()
                            }.addOnFailureListener {
                                errorMessage = it.message.toString()
                            }
                    }
                }
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getBirthdate(): LocalDate?{
        if (_auth.currentUser != null){
            //first check if birthdate exists
            try {
                val document = _auth.currentUser?.let { _db.collection("userdetails").document(it.uid) }
                    ?.get()?.await()
                if (document != null){
                    val previousBirthDate = document.getTimestamp("birthdate")
                    return if (previousBirthDate != null){
                        TimestampToLocalDate(previousBirthDate)
                    } else{
                        LocalDate.now()
                    }
                }
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
        return LocalDate.now()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun saveLastDonationDate(
        timestamp: Timestamp?,
        goto_nextpage:()->Unit={}){
        val date = mapOf(
            "lastDonationDate" to timestamp
        )
        if (_auth.currentUser != null){
            //first check if last donation date already exists
            try {
                val document = _auth.currentUser?.let { _db.collection("userdetails").document(it.uid) }
                    ?.get()?.await()
                if (document != null){
                    val previousLastDate = document.getDate("lastDonationDate")
                    if (previousLastDate != null){
                        //update the existing last date
                        _db.collection("userdetails").document(_auth.currentUser!!.uid)
                            .update("lastDonationDate", timestamp)
                            .addOnSuccessListener {
                                goto_nextpage()
                            }.addOnFailureListener {
                                errorMessage = it.message.toString()
                            }
                    }
                    else{
                        //add new last date
                        _db.collection("userdetails").document(_auth.currentUser!!.uid)
                            .set(date, SetOptions.merge())
                            .addOnSuccessListener {
                                goto_nextpage()
                            }.addOnFailureListener {
                                errorMessage = it.message.toString()
                            }
                    }
                }
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }

    fun saveRegistrationEntryByString(
        entry:String,                                 //what type of entry we want to store, username, gender, area etc
        data:String,                                  //value of the entry
        goto_nextpage: () -> Unit = {}){              //optional parameter, we may want to go somewhere on success
        when (entry){
            "username", "gender", "area", "phoneNo", "bloodGroup"->{
                val datamap = mapOf(entry to data)
                if (_auth.currentUser != null){
                    _db.collection("userdetails").document(_auth.currentUser!!.uid)
                        .set(datamap, SetOptions.merge())
                        .addOnSuccessListener {
                            goto_nextpage()
                        }.addOnFailureListener {
                            errorMessage = it.message.toString()
                        }
                }
            }
            else->{
                errorMessage = "Error: Invalid entry type in firebase"
            }
        }
    }

    suspend fun getRegistrationType():String{
        try {
            val document = _auth.currentUser?.let { _db.collection("userdetails").document(it.uid) }
                ?.get()?.await()
            if (document != null){
                return document.getString("registration_type").toString()
            }
            else return ""
        }catch (e:Exception){
            errorMessage = e.message.toString()
        }
        return ""
    }

    @SuppressLint("NewApi")
    fun saveAadharData(goto_nextpage: () -> Unit = {}){
        val data1 = mapOf("aadharNo" to tempRegistrationDetails.aadharNo)
        if (_auth.currentUser != null){
            _db.collection("aadhardetails").document(_auth.currentUser!!.uid)
                .set(data1, SetOptions.merge())
                .addOnSuccessListener {
                    goto_nextpage()
                }.addOnFailureListener {
                    errorMessage = it.message.toString()
                }
        }
        val data2 = mapOf("aadharDOB" to tempRegistrationDetails.aadharDOB)
        if (_auth.currentUser != null){
            _db.collection("aadhardetails").document(_auth.currentUser!!.uid)
                .set(data2, SetOptions.merge())
                .addOnSuccessListener {
                    goto_nextpage()
                }.addOnFailureListener {
                    errorMessage = it.message.toString()
                }
        }
    }

    fun saveAadharStatus(status:String) {
        val data = mapOf("aadharStatus" to status)
        if (_auth.currentUser != null) {
            _db.collection("aadhardetails").document(_auth.currentUser!!.uid)
                .set(data, SetOptions.merge())
                .addOnSuccessListener {

                }.addOnFailureListener {
                    errorMessage = it.message.toString()
                }
        }
    }
}