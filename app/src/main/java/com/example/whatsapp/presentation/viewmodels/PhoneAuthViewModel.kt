package com.example.whatsapp.presentation.viewmodels

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.whatsapp.model.PhoneAuthUser
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import java.io.ByteArrayOutputStream
import android.util.Base64

@HiltViewModel
class PhoneAuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth, private val firebaseDatabase: FirebaseDatabase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Ideal)
    val authState = _authState.asStateFlow()

    private val userRef = firebaseDatabase.reference.child("users")

    fun sendVerificationCode(phoneNumber: String, activity: Activity) {
        _authState.value = AuthState.Loading
        val option = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                Log.d("PhoneAuth", "onCodeSent Triggered. verification ID: $id")
                _authState.value = AuthState.CodeSend(verificationId = id)
            }

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signWithCredential(credential, context = activity)
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Log.e("PhoneAuth", "Verification Failed : ${exception.message}")
                _authState.value = AuthState.Error(exception.message ?: "Verification Failed")
            }
        }
        val phoneAuthOptions = PhoneAuthOptions.newBuilder(firebaseAuth).setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS).setActivity(activity).setCallbacks(option).build()

        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }

    private fun signWithCredential(credential: PhoneAuthCredential, context: Activity) {

        _authState.value = AuthState.Loading

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = firebaseAuth.currentUser
                val phoneAuthUser = PhoneAuthUser(
                    userId = user?.uid ?: "", phoneNumber = user?.phoneNumber ?: ""
                )

                markUserAsSignedIn(context)
                _authState.value = AuthState.Success(phoneAuthUser)

                fetchUserProfile(user?.uid ?: "")
            } else {
                _authState.value = AuthState.Error(task.exception?.message ?: "Sign-in Failed")
            }
        }
    }

    private fun markUserAsSignedIn(context: Context) {
        val sharedPreference = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreference.edit().putBoolean("isSignedIn", true)
    }

    private fun fetchUserProfile(userId: String) {
        val userRef = userRef.child(userId)
        userRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val userProfile = snapshot.getValue(PhoneAuthUser::class.java)
                if (userProfile != null) {
                    _authState.value = AuthState.Success(userProfile)
                }
            }
        }.addOnFailureListener {
            _authState.value = AuthState.Error("Failed to fetch user profile")
        }
    }

    fun verifyCode(otp: String, context: Context) {
        val currentAuthState = _authState.value

        if (currentAuthState !is AuthState.CodeSend || currentAuthState.verificationId.isEmpty()) {
            Log.d("PhoneAuth", "Attempting to verify OTP without a valid verification ID")

            _authState.value = AuthState.Error("Verification not started or invalid ID")
            return
        }

        val credential = PhoneAuthProvider.getCredential(currentAuthState.verificationId, otp)
        signWithCredential(credential, context as Activity)
    }

    fun saveUserProfile(userId: String, name: String, status: String, profileImage: Bitmap?) {

        val database = FirebaseDatabase.getInstance().reference

        val encodedImage = profileImage?.let { convertBitmapToBase64(it) }
        val userProfile = PhoneAuthUser(
            userId = userId,
            name = name,
            status = status,
            phoneNumber = Firebase.auth.currentUser?.phoneNumber ?: "",
            profileImage = encodedImage
        )
        database.child("users").child(userId).setValue(userProfile)
    }

    private fun convertBitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
    }

    fun resetAuthState() {
        _authState.value = AuthState.Ideal
    }

    fun signOut(activity: Activity) {
        firebaseAuth.signOut()
            val sharedPreference = activity.getSharedPreferences("app_prefs", Activity.MODE_PRIVATE)
        sharedPreference.edit().putBoolean("isSigned", false).apply()
    }
}

sealed class AuthState {
    object Ideal : AuthState()
    object Loading : AuthState()
    data class CodeSend(val verificationId: String) : AuthState()
    data class Success(val user: PhoneAuthUser) : AuthState()
    data class Error(val message: String) : AuthState()
}