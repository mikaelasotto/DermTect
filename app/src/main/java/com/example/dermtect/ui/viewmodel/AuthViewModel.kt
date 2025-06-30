package com.example.dermtect.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dermtect.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    private val auth = FirebaseAuth.getInstance() // ✅ Add this line

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _authSuccess = MutableStateFlow(false)
    val authSuccess: StateFlow<Boolean> = _authSuccess

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun register(email: String, password: String) {
        _loading.value = true
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { emailTask ->
                            if (emailTask.isSuccessful) {
                                FirebaseAuth.getInstance().signOut() // ✅ force logout
                                _authSuccess.value = true
                            } else {
                                _errorMessage.value = "Failed to send verification email."
                            }
                            _loading.value = false
                        }

                } else {
                    _loading.value = false
                    _errorMessage.value = task.exception?.message ?: "Registration failed"
                }
            }
    }

    fun login(email: String, password: String) {
        _loading.value = true
        authUseCase.loginUser(email, password)
            .addOnCompleteListener { task ->
                _loading.value = false
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user?.isEmailVerified == true) {
                        _authSuccess.value = true
                    } else {
                        _errorMessage.value = "Please verify your email first."
                        auth.signOut()
                    }
                } else {
                    _errorMessage.value = task.exception?.message ?: "Login failed"
                }
            }
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun resetAuthSuccess() {
        _authSuccess.value = false
    }
}
