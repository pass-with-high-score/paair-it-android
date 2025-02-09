package app.pwhs.paair_it.core.networking

import app.pwhs.paair_it.BuildConfig
import app.pwhs.paair_it.core.datastore.TokenManager
import app.pwhs.paair_it.data.dto.RefreshTokenDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.forms.submitForm
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import timber.log.Timber

object HttpClientFactory {

    fun create(
        engine: HttpClientEngine,
        tokenManager: TokenManager,
    ): HttpClient {
        val client = HttpClient(engine) {
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.ANDROID
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 3000
                requestTimeoutMillis = 3000
                connectTimeoutMillis = 3000
            }
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(
                            accessToken = tokenManager.accessToken.first() ?: "",
                            refreshToken = tokenManager.refreshToken.first() ?: ""
                        )
                    }
                    refreshTokens {
                        val oldRefreshToken = tokenManager.refreshToken.first() ?: ""
                        val refreshToken: RefreshTokenDto = client.submitForm(
                            url = "auth/refresh",
                            formParameters = parameters {
                                append("refresh_token", oldRefreshToken)
                            }
                        ) { markAsRefreshTokenRequest() }.body()
                        tokenManager.saveAccessToken(refreshToken.accessToken)
                        tokenManager.saveRefreshToken(refreshToken.refreshToken)

                        BearerTokens(
                            accessToken = refreshToken.accessToken,
                            refreshToken = refreshToken.refreshToken
                        )
                    }

                    sendWithoutRequest {
                        it.url.host == BuildConfig.BASE_URL
                    }
                }
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                url {
                    host = BuildConfig.BASE_URL
                    protocol = URLProtocol.HTTPS
                }
            }
        }
        return client
    }
}