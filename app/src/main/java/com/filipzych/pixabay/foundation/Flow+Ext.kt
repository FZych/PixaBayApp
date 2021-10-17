package com.filipzych.pixabay.foundation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> flowSingle(action: suspend () -> T): Flow<T> = flow { emit(action.invoke()) }