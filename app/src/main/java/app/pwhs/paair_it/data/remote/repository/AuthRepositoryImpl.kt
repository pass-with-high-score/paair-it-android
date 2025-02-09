package app.pwhs.paair_it.data.remote.repository

import app.pwhs.paair_it.core.domain.NetworkError
import app.pwhs.paair_it.core.domain.Result
import app.pwhs.paair_it.core.domain.map
import app.pwhs.paair_it.core.networking.safeCall
import app.pwhs.paair_it.data.dto.RefreshTokenDto
import app.pwhs.paair_it.data.mapper.toEntity
import app.pwhs.paair_it.domain.entity.auth.RefreshTokenEntity
import app.pwhs.paair_it.domain.repository.AuthRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.path

class AuthRepositoryImpl(
    private val client: HttpClient
) : AuthRepository {
    override suspend fun refreshToken(refreshToken: String): Result<RefreshTokenEntity, NetworkError> {
        return safeCall<RefreshTokenDto> {
            client.get{
                url {
                    path("/posts")
                }
            }
        }.map {
            it.toEntity()
        }

    }
}