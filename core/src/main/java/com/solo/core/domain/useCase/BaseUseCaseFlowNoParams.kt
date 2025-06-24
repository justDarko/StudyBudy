package com.solo.core.domain.useCase

import com.solo.core.CustomResult
import kotlinx.coroutines.flow.Flow

abstract class BaseUseCaseFlowNoParams<out Type> where Type : Any {
    abstract operator fun invoke(): Flow<CustomResult<Type>>
}