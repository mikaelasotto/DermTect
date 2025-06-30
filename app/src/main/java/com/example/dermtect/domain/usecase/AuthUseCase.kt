package com.example.dermtect.domain.usecase

import com.example.dermtect.data.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class AuthUseCase(private val repository: AuthRepository) {

    fun registerUser(email: String, password: String): Task<AuthResult> {
        return repository.register(email, password)
    }

    fun loginUser(email: String, password: String): Task<AuthResult> {
        return repository.login(email, password)
    }
}
