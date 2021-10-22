package com.filipzych.pixabay.foundation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@OpenForTesting
abstract class BaseUseCase<Input, Output> {

    protected abstract fun onBuild(params: Input): Flow<Output>

    private fun defaultBackgroundScheduler() = Dispatchers.IO

    fun build(params: Input): Flow<Output> =
        flow { emitAll(onBuild(params)) }.flowOn(defaultBackgroundScheduler())
}