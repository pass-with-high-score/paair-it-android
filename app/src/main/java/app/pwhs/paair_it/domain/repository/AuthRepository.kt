package app.pwhs.paair_it.domain.repository

import app.pwhs.paair_it.core.domain.NetworkError
import app.pwhs.paair_it.core.domain.Result
import app.pwhs.paair_it.domain.entity.auth.RefreshTokenEntity

interface AuthRepository {
    suspend fun refreshToken(refreshToken: String): Result<RefreshTokenEntity, NetworkError>
}