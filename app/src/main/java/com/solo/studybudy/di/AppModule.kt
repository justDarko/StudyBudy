package com.solo.studybudy.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.solo.core.data.USER_PREFERENCES
import com.solo.core.data.local.dataStore.DataStoreManager
import com.solo.core.data.repository.AuthRepositoryImpl
import com.solo.core.data.repository.InterestsRepositoryImpl
import com.solo.core.data.repository.UserRepositoryImpl
import com.solo.core.domain.repository.AuthRepository
import com.solo.core.domain.repository.InterestsRepository
import com.solo.core.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()


    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore,
        dataStoreManager: DataStoreManager
    ): AuthRepository {
        return AuthRepositoryImpl(
            firebaseAuth = firebaseAuth,
            firebaseFirestore = firebaseFirestore,
            dataStoreManager = dataStoreManager
        )
    }

    @Provides
    fun provideInterestsRepository(
        firebaseFirestore: FirebaseFirestore
    ): InterestsRepository {
        return InterestsRepositoryImpl(
            firebaseFirestore = firebaseFirestore
        )
    }

    @Provides
    fun provideUserRepository(
        firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore
    ): UserRepository {
        return UserRepositoryImpl(
            firebaseAuth = firebaseAuth, firebaseFirestore = firebaseFirestore
        )
    }

    // Data Store ->
    // Data Store ->
    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            // SupervisorJob allows for child coroutine to continue running in case
            // other child coroutine failed. So if one read/write operation fails,
            // the rest of the work continues
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }
}