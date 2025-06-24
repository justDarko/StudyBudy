package com.solo.core.domain.repository

import com.solo.core.CustomResult
import com.solo.core.domain.model.Interest
import kotlinx.coroutines.flow.Flow

interface InterestsRepository {
    fun getInterests(): Flow<CustomResult<List<Interest>>>
}