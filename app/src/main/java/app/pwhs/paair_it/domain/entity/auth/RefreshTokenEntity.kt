package app.pwhs.paair_it.domain.entity.auth

data class RefreshTokenEntity(
    val refreshToken: String,
    val accessToken: String
)
