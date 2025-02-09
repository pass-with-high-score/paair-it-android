package app.pwhs.paair_it.core.extension

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_preferences")
val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")