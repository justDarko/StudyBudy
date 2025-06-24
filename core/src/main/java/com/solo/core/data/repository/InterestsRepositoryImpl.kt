package com.solo.core.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.solo.core.CustomResult
import com.solo.core.data.FIREBASE_INTERESTS_COLLECTION
import com.solo.core.domain.model.Interest
import com.solo.core.domain.repository.InterestsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class InterestsRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : InterestsRepository {

    override fun getInterests(): Flow<CustomResult<List<Interest>>> = flow {
        try {
            val snapshot = firebaseFirestore.collection(FIREBASE_INTERESTS_COLLECTION).get().await()

            val interests = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Interest::class.java)?.copy(id = doc.id)
            }

            emit(CustomResult.Success(interests))
        } catch (e: Exception) {
            emit(CustomResult.Failure(e.localizedMessage ?: "Error getting the interests"))
        }
    }
}