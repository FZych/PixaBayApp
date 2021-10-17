package com.filipzych.pixabay

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Rule
import org.junit.rules.TestRule

open class BaseViewModelTest {

    protected val instantExecutorRule = TestCoroutineDispatcher()

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var coroutineDispatherRule = CoroutineDispatcherRule(instantExecutorRule)


}
