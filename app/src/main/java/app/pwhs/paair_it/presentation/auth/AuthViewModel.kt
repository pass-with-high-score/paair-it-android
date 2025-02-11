package app.pwhs.paair_it.presentation.auth

import androidx.lifecycle.ViewModel
import app.pwhs.paair_it.domain.repository.AuthRepository

class AuthViewModel(
    val authRepository: AuthRepository
) : ViewModel() {
    init {

    }
}