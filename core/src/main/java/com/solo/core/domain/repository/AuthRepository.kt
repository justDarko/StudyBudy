package com.solo.core.domain.repository

import com.solo.core.CustomResult
import com.solo.core.domain.model.User

interface AuthRepository {
    suspend fun registerUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): CustomResult<Boolean>

    suspend fun loginUser(
        email: String,
        password: String
    ): CustomResult<User>
}