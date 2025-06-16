package com.solo.core.domain.useCase

import com.solo.core.CustomResult

abstract class BaseUseCaseNoParams<out Type> where Type : Any {
    abstract suspend operator fun invoke(): CustomResult<Type>
}