package com.example.dermtect.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dermtect.domain.usecase.AuthUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _authSuccess = MutableStateFlow(false)
    val authSuccess: StateFlow<Boolean> = _authSuccess

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private fun logAudit(uid: String?, email: String?, action: String) {
        val db = FirebaseFirestore.getInstance()
        val logEntry = hashMapOf(
            "uid" to uid,
            "email" to email,
            "action" to action,
            "timestamp" to FieldValue.serverTimestamp()
        )

        Log.d("AuditLog", "Logging action: $action for uid=$uid email=$email")

        db.collection("audit_logs").add(logEntry)
            .addOnSuccessListener {
                Log.d("AuditLog", "Audit log saved.")
            }
            .addOnFailureListener {
                Log.e("AuditLog", "Failed to log audit event: ${it.message}")
            }
    }


    fun register(email: String, password: String, firstName: String, lastName: String) {
        _loading.value = true
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    val formattedFirstName = firstName.lowercase().replaceFirstChar { it.uppercaseChar().toString() }
                    val formattedLastName = lastName.lowercase().replaceFirstChar { it.uppercaseChar().toString() }


                    val userData = hashMapOf(
                        "uid" to user?.uid,
                        "email" to user?.email,
                        "firstName" to formattedFirstName,
                        "lastName" to formattedLastName,
                        "createdAt" to FieldValue.serverTimestamp(),
                        "emailVerified" to false,
                        "role" to "patient"
                    )

                    user?.let {
                        FirebaseFirestore.getInstance()
                            .collection("users")
                            .document(it.uid)
                            .set(userData)
                            .addOnSuccessListener {
                                user.sendEmailVerification()
                                    .addOnCompleteListener { emailTask ->
                                        if (emailTask.isSuccessful) {
                                            logAudit(user.uid, user.email, "account_created")
                                            FirebaseAuth.getInstance().signOut()
                                            _authSuccess.value = true
                                        } else {
                                            _errorMessage.value = "Failed to send verification email."
                                        }
                                        _loading.value = false
                                    }
                            }
                            .addOnFailureListener {
                                _loading.value = false
                                _errorMessage.value = "Failed to save user info."
                            }
                    } ?: run {
                        _loading.value = false
                        _errorMessage.value = "Registration failed: no user."
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
                    user?.reload()?.addOnCompleteListener { reloadTask ->
                        if (reloadTask.isSuccessful) {
                            if (user.isEmailVerified) {
                                FirebaseFirestore.getInstance()
                                    .collection("users")
                                    .document(user.uid)
                                    .update("emailVerified", true)
                                    .addOnSuccessListener {
                                        logAudit(user.uid, user.email, "login")
                                        _authSuccess.value = true
                                    }
                                    .addOnFailureListener {
                                        _errorMessage.value = "Verified but failed to update Firestore."
                                    }
                            } else {
                                _errorMessage.value = "Please verify your email first."
                                auth.signOut()
                            }
                        } else {
                            _errorMessage.value = "Failed to reload user info."
                        }
                    }
                } else {
                    _errorMessage.value = task.exception?.message ?: "Login failed"
                }
            }
    }

    fun signInWithGoogle(idToken: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val uid = user?.uid ?: return@addOnCompleteListener

                    val db = FirebaseFirestore.getInstance()
                    val userDoc = db.collection("users").document(uid)

                    userDoc.get()
                        .addOnSuccessListener { document ->
                            if (!document.exists()) {
                                val names = user?.displayName?.split(" ") ?: listOf("", "")
                                val firstName = names.firstOrNull() ?: ""
                                val lastName = names.getOrNull(1) ?: ""

                                val userData = mapOf(
                                    "uid" to uid,
                                    "email" to user.email,
                                    "firstName" to firstName,
                                    "lastName" to lastName,
                                    "role" to "patient",
                                    "createdAt" to FieldValue.serverTimestamp()
                                )

                                userDoc.set(userData)
                                    .addOnSuccessListener {
                                        logAudit(uid, user.email, "google_sign_in")
                                        onSuccess()
                                    }
                                    .addOnFailureListener { onError("Failed to save user to Firestore") }
                            } else {
                                logAudit(uid, user.email, "google_sign_in")
                                onSuccess()
                            }
                        }
                        .addOnFailureListener {
                            onError("Failed to check user document")
                        }
                } else {
                    onError(task.exception?.message ?: "Google Sign-In Failed")
                }
            }
    }

    fun logout() {
        val user = auth.currentUser
        logAudit(user?.uid, user?.email, "logout")
        auth.signOut()
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun resetAuthSuccess() {
        _authSuccess.value = false
    }

}
