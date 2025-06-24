package com.solo.core.domain.useCase

import com.solo.core.CustomResult
import com.solo.core.domain.model.Interest
import com.solo.core.domain.repository.InterestsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInterestsUseCase @Inject constructor(
    private val interestsRepository: InterestsRepository
) : BaseUseCaseFlowNoParams<List<Interest>>() {

    override fun invoke(): Flow<CustomResult<List<Interest>>> {
        return interestsRepository.getInterests()
    }
}