package app.pwhs.paair_it.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenDto(
    val refreshToken: String,
    val accessToken: String
)
