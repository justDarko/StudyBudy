package com.solo.core.data.local.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.solo.core.domain.model.User
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    init {
        Timber.d("Hello form DataStoreManager")
    }

    suspend fun clearPreferences() {
        dataStore.edit {
            it.clear()
        }
    }

    suspend fun saveUser(user: User) {
        dataStore.edit { prefs ->
            prefs[stringPreferencesKey("USER_ID")] = user.id
            prefs[stringPreferencesKey("USER_FIRST_NAME")] = user.firstName
            prefs[stringPreferencesKey("USER_LAST_NAME")] = user.lastName
            prefs[stringPreferencesKey("USER_EMAIL")] = user.email
            prefs[stringPreferencesKey("USER_JOB_TITLE")] = user.jobTitle
            prefs[stringPreferencesKey("USER_INTERESTS")] = user.userInterests.joinToString(",")
        }
    }

    suspend fun getUser(): User {
        val prefs = dataStore.data.first()
        return User(
            id = prefs[stringPreferencesKey("USER_ID")] ?: "",
            firstName = prefs[stringPreferencesKey("USER_FIRST_NAME")] ?: "",
            lastName = prefs[stringPreferencesKey("USER_LAST_NAME")] ?: "",
            email = prefs[stringPreferencesKey("USER_EMAIL")] ?: "",
            jobTitle = prefs[stringPreferencesKey("USER_JOB_TITLE")] ?: "",
            userInterests = prefs[stringPreferencesKey("USER_INTERESTS")]?.split(",") ?: emptyList()
        )
    }
}