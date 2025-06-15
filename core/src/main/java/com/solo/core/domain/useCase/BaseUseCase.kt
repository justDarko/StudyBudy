package com.solo.core.domain.useCase

import com.solo.core.CustomResult

abstract class BaseUseCase<in Params, out Type> where Type : Any {
    abstract suspend fun invoke(params: Params):
            CustomResult<Type>

    suspend fun invoke(): CustomResult<Type> {
        return invoke(Unit as Params)
    }
}