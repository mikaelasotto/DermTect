package com.example.dermtect.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthRepository {
    fun register(email: String, password: String): Task<AuthResult>
    fun login(email: String, password: String): Task<AuthResult>
}