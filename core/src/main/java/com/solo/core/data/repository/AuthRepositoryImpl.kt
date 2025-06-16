package com.solo.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.solo.core.CustomResult
import com.solo.core.data.FIREBASE_USERS_COLLECTION
import com.solo.core.data.remote.requestModel.UserRequestModel
import com.solo.core.domain.model.User
import com.solo.core.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): CustomResult<Boolean> {
        if (password != confirmPassword) {
            return CustomResult.Failure("Passwords do not match")
        }

        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

            val firebaseUser = result.user ?: return CustomResult.Failure("User creation failed")

            val userModel = UserRequestModel(
                id = firebaseUser.uid, firstName = firstName, lastName = lastName, email = email
            )

            firebaseFirestore.collection(FIREBASE_USERS_COLLECTION).document(firebaseUser.uid)
                .set(userModel).await()

            CustomResult.Success(true)
        } catch (e: Exception) {
            CustomResult.Failure(e.message ?: "Unknown error")
        }
    }

    override suspend fun loginUser(email: String, password: String): CustomResult<User> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()

            val firebaseUser = result.user ?: return CustomResult.Failure("Login failed")

            val snapshot = firebaseFirestore.collection(FIREBASE_USERS_COLLECTION)
                .document(firebaseUser.uid)
                .get()
                .await()

            if (!snapshot.exists()) {
                return CustomResult.Failure("User record not found in Firestore")
            }

            val user = snapshot.toObject(User::class.java)
                ?: return CustomResult.Failure("Failed to parse user data")

            CustomResult.Success(user)
        } catch (e: Exception) {
            CustomResult.Failure(e.message ?: "Unknown error")
        }
    }

    override suspend fun isUserLoggedIn(): CustomResult<Boolean> {
        return try {
            val isLoggedIn = firebaseAuth.currentUser != null
            CustomResult.Success(isLoggedIn)
        } catch (e: Exception) {
            CustomResult.Failure("Failed to check login state", e)
        }
    }
}