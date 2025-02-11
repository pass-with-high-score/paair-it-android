package app.pwhs.paair_it.core.di

import app.pwhs.paair_it.core.datastore.TokenManager
import app.pwhs.paair_it.core.networking.HttpClientFactory
import app.pwhs.paair_it.data.remote.repository.AuthRepositoryImpl
import app.pwhs.paair_it.domain.repository.AuthRepository
import app.pwhs.paair_it.presentation.auth.AuthViewModel
import app.pwhs.paair_it.presentation.home.HomeViewModel
import app.pwhs.paair_it.presentation.onboarding.OnboardingViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind

val appModule = module {
    single { TokenManager(get()) }
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    single { HttpClientFactory.create(CIO.create(), get()) }
    viewModelOf(::HomeViewModel)
    viewModel { AuthViewModel(get()) }
    viewModel { OnboardingViewModel(get()) }
}