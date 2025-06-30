package com.example.dermtect.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepositoryImpl : AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun register(email: String, password: String): Task<AuthResult> {
        val auth = FirebaseAuth.getInstance()
        val firestore = FirebaseFirestore.getInstance()

        return auth.createUserWithEmailAndPassword(email, password).apply {
            addOnSuccessListener { result ->
                val user = result.user
                user?.let {
                    val userData = hashMapOf(
                        "uid" to it.uid,
                        "email" to email,
                        "createdAt" to System.currentTimeMillis()
                    )

                    firestore.collection("users")
                        .document(it.uid)
                        .set(userData)
                }
            }
        }
    }


    override fun login(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }
}