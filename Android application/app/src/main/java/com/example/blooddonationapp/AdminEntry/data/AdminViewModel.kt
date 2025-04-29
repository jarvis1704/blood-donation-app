package com.example.blooddonationapp.AdminEntry.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blooddonationapp.global.data.PhoneNo
import com.example.blooddonationapp.global.data.PhoneNoList
import com.example.blooddonationapp.global.data.errorMessage
import com.example.blooddonationapp.global.data.infoMessage
import com.example.blooddonationapp.home.data.TimestampToLocalDateTime
import com.example.blooddonationapp.home.data.announcement
import com.example.blooddonationapp.home.data.bloodRequest
import com.example.blooddonationapp.home.data.globalAnnouncementList
import com.example.blooddonationapp.home.data.globalBloodRequestList
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
): ViewModel() {

    fun newBloodReq(goto_activeBloodreqs:()-> Unit = {}, onsuccess:()-> Unit={}){
        try {
            val datamap = mapOf(
                "patient" to newBloodRequest.patientname,
                "patientage" to newBloodRequest.patientage,
                "patientgender" to newBloodRequest.patientgender,
                "attendant" to newBloodRequest.attendantname,
                "attendantphoneno" to newBloodRequest.attendantphoneno,
                "bloodtype" to newBloodRequest.bloodgroup,
                "hospital" to newBloodRequest.hospital,
                "urgencylevel" to newBloodRequest.urgencylevel,
                "unitsrequired" to newBloodRequest.unitsrequired,
                "details" to newBloodRequest.details,
                "date" to Timestamp.Companion.now()
            )
            db.collection("blood_requests").document()
                .set(datamap, SetOptions.merge())
                .addOnSuccessListener {
                    onsuccess()
                    ClearNewBloodReqObj()
                    goto_activeBloodreqs()
                }
                .addOnFailureListener {
                    errorMessage = it.message.toString()
                }
        }catch (e:Exception){
            errorMessage = e.message.toString()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun newAnnouncement(goto_activeAnnouncements:()-> Unit){
        val localDateTime = newAnnouncement.date.atTime(newAnnouncement.time)
        val instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant()
        val timestamp = Timestamp(instant.epochSecond, instant.nano)

        try {
            val datamap = mapOf(
                "title" to newAnnouncement.title,
                "location" to newAnnouncement.location,
                "date&time" to timestamp
            )
            if (true){
                db.collection("announcements").document()
                    .set(datamap, SetOptions.merge())
                    .addOnSuccessListener {
                        ClearNewAnnouncementObj()
                        goto_activeAnnouncements()
                    }
                    .addOnFailureListener {
                        errorMessage = it.message.toString()
                    }
            }
        }catch (e:Exception){
            errorMessage = e.message.toString()
        }
    }

    fun getPendingAadhar(){
        viewModelScope.launch {
            try {
                val aadharlist = db.collection("aadhardetails")
                    .whereEqualTo("aadharStatus", "submitted")
                    .get().await()
                if (aadharlist != null){
                    val aadharList = aadharlist.documents.mapNotNull { doc->
                        val data = doc.data
                        val temp = doc.id
                        data?.let {
                            aadharUser(
                                id = temp,
                                userid = it["useruid"].toString(),
                                useremail = it["useremail"].toString(),
                                aadharNo = it["aadharNo"].toString().toLongOrNull(),
                                aadharDOB = it["aadharDOB"].toString().toLongOrNull(),
                                aadharStatus = it["aadharStatus"].toString(),
                                aadharPhotoString = it["imageData"].toString()
                            )
                        }
                    }.filter { it.aadharStatus=="submitted" }
                    aadharPendingList = aadharList
                }
            }catch (e:Exception){
                errorMessage =e.message.toString()
            }
        }
    }

    fun DeleteBloodRequest(req: bloodRequest){
        viewModelScope.launch {
            try {
                db.collection("blood_requests").document(req.id)
                    .delete()
                    .addOnSuccessListener {
                        globalBloodRequestList = globalBloodRequestList?.minus(req)
                    }
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }

    fun DeleteAnnouncement(announcement: announcement){
        viewModelScope.launch {
            try {
                db.collection("announcements").document(announcement.id)
                    .delete()
                    .addOnSuccessListener {
                        globalAnnouncementList = globalAnnouncementList?.minus(announcement)
                    }
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }

    fun GetActivePasskeys(){
        ActivePasskeysList = emptyList()
        viewModelScope.launch {
            try {
                val document = db.collection("passkey").get().await()
                val list = document.documents.mapNotNull { doc->
                    val temp = doc.id
                    val data = doc.data
                    data?.let {
                        Passkey(
                            id = temp,
                            key = it["passkey"].toString()
                        )
                    }
                }
                ActivePasskeysList = list
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }finally {
                isFetchingPasskeys = false
            }
        }
    }

    fun DeletePasskey(key: Passkey){
        if (ActivePasskeysList.size > 1){
            viewModelScope.launch {
                try {
                    db.collection("passkey").document(key.id)
                        .delete()
                        .addOnSuccessListener {
                            ActivePasskeysList = ActivePasskeysList.minus(key)
                        }
                }catch (e:Exception){
                    errorMessage = e.message.toString()
                }
            }
        }else{
            errorMessage = "Minimum 1 passkey is required!"
        }
    }

    fun AddPasskey(key: String){
        if (key.isNotEmpty()){
            viewModelScope.launch {
                try {
                    val data = mapOf(
                        "passkey" to key
                    )
                    db.collection("passkey").add(data)
                        .addOnSuccessListener {
                            tempNewPasskey = ""
                            GetActivePasskeys()
                            isNewPasskeyDialogue = false
                        }
                }catch (e: Exception){
                    errorMessage = e.message.toString()
                }
            }
        }else{
            errorMessage = "Key cannot be empty"
        }
    }

    fun getPendingBloodRequests(){
        viewModelScope.launch {
            try {
                val list = db.collection("pending_blood_requests").get().await()
                if (list != null){
                    val List = list.documents.mapNotNull { doc->
                        val data = doc.data
                        val id = doc.id
                        data?.let {
                            bloodRequest(
                                id = id,
                                patientname = it["patient"].toString(),
                                patientage = it["patientage"].toString(),
                                patientgender = it["patientgender"].toString(),
                                attendantname = it["attendant"].toString(),
                                attendantphoneno = it["attendantphoneno"].toString(),
                                bloodtype = it["bloodtype"].toString(),
                                hospital = it["hospital"].toString(),
                                urgencylevel = it["urgencylevel"].toString(),
                                unitsrequired = it["unitsrequired"].toString(),
                                details = it["details"].toString(),
                                date = (it["date"] as? Timestamp)?.let { timestamp ->
                                    TimestampToLocalDateTime(timestamp)
                                }
                            )
                        }
                    }.sortedByDescending { it.date }
                    bloodreqPendingList = List
                }
            }catch (e:Exception){
                errorMessage =e.message.toString()
            }
        }
    }

    fun DeletePendingBloodReq(id: String){
        viewModelScope.launch {
            try {
                db.collection("pending_blood_requests").document(id)
                    .delete()
                    .addOnSuccessListener {
                        infoMessage = "Process Executed Successfully"
                        getPendingBloodRequests()
                    }
            }catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }

    fun newContact(){
        viewModelScope.launch {
            try {
                val datamap = mapOf(
                    "name" to tempNewContact.name,
                    "number" to tempNewContact.number,
                )
                db.collection("phone_nos").document()
                    .set(datamap, SetOptions.merge())
                    .addOnSuccessListener {
                        ClearTempNewContact()
                        isNewContactDialogue = false
                        infoMessage = "Contact added successfully"
                        GetImportantPhoneNo()
                    }
                    .addOnFailureListener {
                        errorMessage = it.message.toString()
                    }
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }

    fun GetImportantPhoneNo(){
        isFetchingNumbers = true
        viewModelScope.launch {
            try {
                val list = db.collection("phone_nos").get().await()
                if (list != null) {
                    val List = list.documents.mapNotNull { doc ->
                        val temp = doc.id
                        val data = doc.data
                        data?.let {
                            PhoneNo(
                                id = temp,
                                name = it["name"].toString(),
                                number = it["number"].toString()
                            )
                        }

                    }
                    PhoneNoList = List
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isFetchingNumbers = false
            }
        }
    }

    fun DeleteContact(id: String){
        viewModelScope.launch {
            try {
                db.collection("phone_nos").document(id)
                    .delete()
                    .addOnSuccessListener {
                        infoMessage = "Contact deleted successfully"
                        GetImportantPhoneNo()
                    }
            }catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun GetUserData(id: String, isDataLoaded: MutableState<Boolean>, userstate: MutableState<AppUser>){
        var temp = AppUser("","","","","", LocalDateTime.now())
        viewModelScope.launch {
            try {
                val list = db.collection("userdetails").document(id)
                    .get().await()
                val data = list.data
                data?.let {
                    temp = AppUser(
                        username = it["username"].toString(),
                        phoneno = it["phoneNo"].toString(),
                        gender = it["gender"].toString(),
                        bloodGroup = it["bloodGroup"].toString(),
                        area = it["area"].toString(),
                        birthdate = TimestampToLocalDateTime(it["birthdate"] as Timestamp)
                    )
                }
            }catch (e: Exception){
                errorMessage=e.message.toString()
            }finally {
                isDataLoaded.value = true
                userstate.value = userstate.value.copy(temp.username, temp.phoneno, temp.gender, temp.bloodGroup, temp.area, temp.birthdate)
            }
        }
    }

    fun SetUserAadhaarStatus(id: String, status: String){
        viewModelScope.launch {
            try {
                val data = db.collection("aadhardetails").document(id)
                data.set(
                    hashMapOf(
                        "aadharStatus" to status
                    )
                ).addOnSuccessListener {
                    getPendingAadhar()
                    infoMessage = "Updated successfully"
                }
                    .await()
            }catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}