package com.solo.core.domain.repository

import com.solo.core.CustomResult
import com.solo.core.domain.model.User

interface UserRepository {
    suspend fun setUserJobTitleAndInterests(
        jobTitle: String,
        interest: String
    ): CustomResult<Boolean>

    suspend fun checkIfUserSetInterests(): CustomResult<Boolean>

    suspend fun getMatchingUsersByInterests(): CustomResult<List<User>>
}