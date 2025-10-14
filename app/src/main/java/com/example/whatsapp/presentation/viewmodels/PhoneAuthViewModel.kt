package com.example.whatsapp.presentation.viewmodels

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.whatsapp.model.PhoneAuthUser
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PhoneAuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
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
                _authState.value= AuthState.CodeSend(verificationId = id)
            }

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                TODO("Not yet implemented")
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Log.e("PhoneAuth","Verification Failed : ${exception.message}")
                _authState.value = AuthState.Error(exception.message ?: "Verification Failed")
            }

        }
    }

}

sealed class AuthState {
    object Ideal : AuthState()
    object Loading : AuthState()
    data class CodeSend(val verificationId: String) : AuthState()
    data class Success(val user: PhoneAuthUser) : AuthState()
    data class Error(val message: String) : AuthState()
}