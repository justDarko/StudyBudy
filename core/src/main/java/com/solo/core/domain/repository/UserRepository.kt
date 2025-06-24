package com.solo.core.domain.repository

import com.solo.core.CustomResult

interface UserRepository {
    suspend fun setUserJobTitleAndInterests(
        jobTitle: String, interests: List<String>
    ): CustomResult<Boolean>

    suspend fun checkIfUserSetInterests(): CustomResult<Boolean>
}