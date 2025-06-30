package com.example.dermtect.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dermtect.data.repository.AuthRepositoryImpl
import com.example.dermtect.domain.usecase.AuthUseCase

class AuthViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = AuthRepositoryImpl()
        val useCase = AuthUseCase(repository)
        return AuthViewModel(useCase) as T
    }
}
