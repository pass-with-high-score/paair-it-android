package app.pwhs.paair_it.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    private val _greeting = mutableStateOf("Hello, World!")
    val greeting: String get() = _greeting.value

    fun updateGreeting(newGreeting: String) {
        _greeting.value = newGreeting
    }
}