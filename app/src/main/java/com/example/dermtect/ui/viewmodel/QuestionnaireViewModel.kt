package com.example.dermtect.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuestionnaireViewModel : ViewModel() {
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess: StateFlow<Boolean> = _saveSuccess

    fun saveQuestionnaireAnswers(
        answers: List<Boolean?>,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        _loading.value = true
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return onError()
        val db = FirebaseFirestore.getInstance()

        val answerMap = answers.mapIndexed { index, value ->
            "question_${index + 1}" to value
        }.toMap()

        val data = mapOf(
            "uid" to uid,
            "answers" to answerMap,
            "timestamp" to FieldValue.serverTimestamp()
        )

        db.collection("questionnaires")
            .document(uid)
            .set(data)
            .addOnSuccessListener {
                _loading.value = false
                _saveSuccess.value = true
                val action = if (existingAnswers.value == null) "questionnaire_answered" else "questionnaire_updated"
                logQuestionnaireAudit(action)
                onSuccess()
            }
            .addOnFailureListener { e ->
                _loading.value = false
                onError()
            }

    }

    fun resetSuccessFlag() {
        _saveSuccess.value = false
    }

    private val _existingAnswers = MutableStateFlow<List<Boolean?>?>(null)
    val existingAnswers: StateFlow<List<Boolean?>?> = _existingAnswers

    fun loadQuestionnaireAnswers() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
        FirebaseFirestore.getInstance()
            .collection("questionnaires")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val data = document["answers"] as? Map<String, Boolean>
                    val loaded = (1..8).map { data?.get("question_$it") }
                    _existingAnswers.value = loaded
                }
            }
    }
    private fun logQuestionnaireAudit(action: String) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val email = FirebaseAuth.getInstance().currentUser?.email ?: "unknown"
        val logData = mapOf(
            "uid" to uid,
            "email" to email,
            "action" to action,
            "timestamp" to FieldValue.serverTimestamp()
        )

        FirebaseFirestore.getInstance()
            .collection("audit_logs")
            .add(logData)
    }

}
