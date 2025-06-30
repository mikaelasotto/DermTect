package com.example.dermtect.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dermtect.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task

class AuthViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _authSuccess = MutableStateFlow(false)
    val authSuccess: StateFlow<Boolean> = _authSuccess

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun register(email: String, password: String) {
        _loading.value = true
        authUseCase.registerUser(email, password)
            .addOnCompleteListener { task ->
                _loading.value = false
                if (task.isSuccessful) {
                    _authSuccess.value = true
                } else {
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
                    _authSuccess.value = true
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
