package com.solo.studybudy.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.solo.core.data.repository.AuthRepositoryImpl
import com.solo.core.data.repository.InterestsRepositoryImpl
import com.solo.core.data.repository.UserRepositoryImpl
import com.solo.core.domain.repository.AuthRepository
import com.solo.core.domain.repository.InterestsRepository
import com.solo.core.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
        firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImpl(
            firebaseAuth = firebaseAuth, firebaseFirestore = firebaseFirestore
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
}