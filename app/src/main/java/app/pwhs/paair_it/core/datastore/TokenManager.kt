package app.pwhs.paair_it.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import app.pwhs.paair_it.core.extension.authDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class TokenManager(private val context: Context) {
    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }

    val accessToken: Flow<String?> = context.authDataStore.data
        .map { preferences ->
            if (preferences[ACCESS_TOKEN] != null) {
                Timber.d("Access token: ${preferences[ACCESS_TOKEN]}")
                preferences[ACCESS_TOKEN]
            } else {
                null
            }
        }

    val refreshToken: Flow<String?> = context.authDataStore.data
        .map { preferences ->
            if (preferences[REFRESH_TOKEN] != null) {
                preferences[REFRESH_TOKEN]
            } else {
                null
            }
        }

    suspend fun saveAccessToken(accessToken: String) {
        context.authDataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
        }
    }

    suspend fun saveRefreshToken(refreshToken: String) {
        context.authDataStore.edit { preferences ->
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }
}