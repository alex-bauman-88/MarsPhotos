package com.example.marsphotos.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.rules.TestWatcher
import org.junit.runner.Description


/**A `TestRule` provides a way to control the environment under which a test is run.
 * A `TestRule` may add additional checks, it may perform necessary setup or cleanup for tests,
 * or it may observe test execution to report it elsewhere.
 * They can be easily shared between test classes.

*The `TestWatcher` class enables you to take actions on different execution phases
 * of a test.
 *
 *
`TestDispatcher` constructor parameter for the `TestDispatcherRule`
enables the use of different dispatchers, such as `StandardTestDispatcher`.
This constructor parameter needs to have a default value set to an instance
of the `UnconfinedTestDispatcher` object. The `UnconfinedTestDispatcher` class
inherits from the `TestDispatcher` class and it specifies that tasks
must not be executed in any particular order.
This pattern of execution is good for simple tests as coroutines are
handled automatically.

Unlike `UnconfinedTestDispatcher`, the `StandardTestDispatcher` class enables
full control over coroutine execution.
This way is preferable for complicated tests that require a manual approach,
but it is not necessary for the tests in this codelab.


The primary goal of this test rule is to replace the `Main` dispatcher with a
test dispatcher before a test begins to execute. The `starting()` function of
the `TestWatcher` class executes before a given test executes.
 * */

class TestDispatcherRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
): TestWatcher() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    // After test execution is finished, reset the Main dispatcher by overriding the finished() method.
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description){
        Dispatchers.resetMain()
    }
}