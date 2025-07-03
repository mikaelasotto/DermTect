package com.example.dermtect.ui.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com.example.dermtect.model.NewsItem
import androidx.lifecycle.AndroidViewModel


class UserHomeViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val uid = auth.currentUser?.uid.orEmpty()

    private val _firstName = MutableStateFlow("User")
    val firstName: StateFlow<String> = _firstName

    private val _hasConsented = MutableStateFlow(false)
    val hasConsented: StateFlow<Boolean> = _hasConsented

    private val _consentChecked = MutableStateFlow(false)
    val consentChecked: StateFlow<Boolean> = _consentChecked



    fun fetchUserInfo() {
        if (uid.isEmpty()) return
        viewModelScope.launch {
            val userDoc = db.collection("users").document(uid).get().await()
            val name = userDoc.getString("firstName")?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            } ?: "User"
            _firstName.value = name
        }
    }

    fun checkConsentStatus() {
        if (uid.isEmpty()) return
        db.collection("users").document(uid).get().addOnSuccessListener { document ->
            val consent = document.getBoolean("privacyConsent") ?: false
            _hasConsented.value = consent
            _consentChecked.value = true
        }
    }

    fun saveUserConsent() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
        viewModelScope.launch {
            try {
                FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(uid)
                    .update("privacyConsent", true)
                    .await()

                _hasConsented.value = true
            } catch (_: Exception) {
                // Handle error if needed
            }
        }
    }

    suspend fun hasAnsweredQuestionnaire(): Boolean {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return false
        val document = FirebaseFirestore.getInstance()
            .collection("questionnaires")
            .document(uid)
            .get()
            .await()

        return document.exists()
    }
    private val _newsItems = MutableStateFlow<List<NewsItem>>(emptyList())
    val newsItems: StateFlow<List<NewsItem>> = _newsItems

    private val _isLoadingNews = MutableStateFlow(false)
    val isLoadingNews: StateFlow<Boolean> = _isLoadingNews

    fun fetchNews() {
        viewModelScope.launch {
            _isLoadingNews.value = true
            try {
                val result = db.collection("news").get().await()
                val items = result.map { doc ->
                    val imageName = doc.getString("imageRes") ?: ""
                    val imageResId = if (imageName.isNotBlank()) {
                        context.resources.getIdentifier(imageName, "drawable", context.packageName)
                            .takeIf { it != 0 }
                    } else null

                    NewsItem(
                        imageResId = imageResId,
                        title = doc.getString("title") ?: "",
                        description = doc.getString("description") ?: "",
                        body = doc.getString("body") ?: "",
                        source = doc.getString("source") ?: "",
                        date = doc.getString("date") ?: ""
                    )
                }
                _newsItems.value = items
            } catch (_: Exception) {
                _newsItems.value = emptyList() // fallback
            } finally {
                _isLoadingNews.value = false
            }
        }
    }


}
