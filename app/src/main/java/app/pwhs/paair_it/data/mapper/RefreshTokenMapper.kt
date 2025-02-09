package app.pwhs.paair_it.data.mapper

import app.pwhs.paair_it.data.dto.RefreshTokenDto
import app.pwhs.paair_it.domain.entity.auth.RefreshTokenEntity

fun RefreshTokenDto.toEntity() = RefreshTokenEntity(
    refreshToken = refreshToken,
    accessToken = accessToken
)

fun RefreshTokenEntity.toDto() = RefreshTokenDto(
    refreshToken = refreshToken,
    accessToken = accessToken
)