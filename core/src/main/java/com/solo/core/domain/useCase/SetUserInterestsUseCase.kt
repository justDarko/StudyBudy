package com.solo.core.domain.useCase

import com.solo.core.CustomResult
import com.solo.core.domain.model.Interest
import com.solo.core.domain.repository.UserRepository
import javax.inject.Inject

class SetUserInterestsUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<SetUserInterestsUseCase.Params, Boolean>() {

    override suspend operator fun invoke(params: Params): CustomResult<Boolean> {
        return userRepository.setUserJobTitleAndInterests(
            interests = params.userInterests,
            jobTitle = params.jobTitle
        )
    }

    data class Params(
        val userInterests: List<String>,
        val jobTitle: String
    )
}