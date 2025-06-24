package com.solo.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.solo.core.CustomResult
import com.solo.core.data.FIREBASE_USERS_COLLECTION
import com.solo.core.data.FIREBASE_USER_INTERESTS
import com.solo.core.data.FIREBASE_USER_JOB_TITLE
import com.solo.core.domain.model.User
import com.solo.core.domain.repository.UserRepository
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth, private val firebaseFirestore: FirebaseFirestore
) : UserRepository {

    override suspend fun setUserJobTitleAndInterests(
        jobTitle: String,
        interests: List<String>
    ): CustomResult<Boolean> {
        return try {
            val userId = firebaseAuth.currentUser?.uid
                ?: return CustomResult.Failure("User not logged in")

            val updates = mapOf(
                FIREBASE_USER_INTERESTS to interests,
                FIREBASE_USER_JOB_TITLE to jobTitle
            )

            firebaseFirestore
                .collection(FIREBASE_USERS_COLLECTION)
                .document(userId)
                .set(updates, SetOptions.merge())
                .await() // suspend until Firestore operation completes

            CustomResult.Success(true)
        } catch (e: Exception) {
            CustomResult.Failure(e.localizedMessage ?: "Error setting user interests")
        }
    }

    override suspend fun checkIfUserSetInterests(): CustomResult<Boolean> {
        return try {
            val userId = firebaseAuth.currentUser?.uid
                ?: return CustomResult.Failure("User not logged in")


            val documentSnapshot = firebaseFirestore
                .collection(FIREBASE_USERS_COLLECTION)
                .document(userId)
                .get()
                .await()

            val user = documentSnapshot.toObject(User::class.java)
            val interests = user?.userInterests

            Timber.d("User interests: $interests")

            val hasInterests = !interests.isNullOrEmpty()
            CustomResult.Success(hasInterests)
        } catch (e: Exception) {
            CustomResult.Failure(e.localizedMessage ?: "Error setting user interests")
        }
    }
}