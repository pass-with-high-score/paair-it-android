package app.pwhs.paair_it.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pwhs.paair_it.domain.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    val authRepository: AuthRepository
) : ViewModel() {
    private val _uiEvent = Channel<OnboardingEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _uiEvent.send(OnboardingEvent.OnboardingCompleted)
        }
    }

}