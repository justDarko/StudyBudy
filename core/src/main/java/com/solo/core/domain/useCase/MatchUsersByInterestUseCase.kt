package com.solo.core.domain.useCase

import com.solo.core.CustomResult
import com.solo.core.domain.model.User
import com.solo.core.domain.repository.UserRepository
import javax.inject.Inject

class MatchUsersByInterestUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCaseNoParams<List<User>>() {

    override suspend fun invoke(): CustomResult<List<User>> {
        return userRepository.getMatchingUsersByInterests()
    }
}