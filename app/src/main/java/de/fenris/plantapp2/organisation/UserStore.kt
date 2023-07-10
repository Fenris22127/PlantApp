package de.fenris.plantapp2.organisation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")
        private val PREF_LANGUAGE = stringPreferencesKey("pref_language")
        private val PREF_DARK_MODE = booleanPreferencesKey("pref_dark_mode")
        private val THEME_SET_BY_USER = booleanPreferencesKey("theme_set_by_user")
    }

    val getDarkModePreference: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[PREF_DARK_MODE] ?: true
    }

    val getLanguagePreference: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PREF_LANGUAGE] ?: ""
    }

    val getThemeSetByUser: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[THEME_SET_BY_USER] ?: false
    }

    suspend fun saveDarkModePreference(token: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PREF_DARK_MODE] = token
        }
    }

    suspend fun saveLanguagePreference(token: String) {
        context.dataStore.edit { preferences ->
            preferences[PREF_LANGUAGE] = token
        }
    }

    suspend fun saveThemeSetByUser(token: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_SET_BY_USER] = token
        }
    }
}