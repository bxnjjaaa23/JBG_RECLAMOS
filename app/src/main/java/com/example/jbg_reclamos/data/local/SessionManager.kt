package com.example.jbg_reclamos.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("session")

class SessionManager(private val context: Context) {

    private val KEY_EMAIL = stringPreferencesKey("email")
    private val KEY_TOKEN = stringPreferencesKey("token")
    private val KEY_ROLE = stringPreferencesKey("role")

    val emailFlow: Flow<String?> = context.dataStore.data.map { it[KEY_EMAIL] }
    val tokenFlow: Flow<String?> = context.dataStore.data.map { it[KEY_TOKEN] }
    val roleFlow: Flow<String?> = context.dataStore.data.map { it[KEY_ROLE] }

    suspend fun save(email: String, token: String, role: String) {
        context.dataStore.edit {
            it[KEY_EMAIL] = email
            it[KEY_TOKEN] = token
            it[KEY_ROLE] = role
        }
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}
