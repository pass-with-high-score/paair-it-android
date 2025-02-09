package app.pwhs.paair_it.presentation.onboarding

sealed class OnboardingEvent {
    data object OnboardingCompleted : OnboardingEvent()
}