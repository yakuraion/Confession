package pro.yakuraion.androidcommon.coroutinestests

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import pro.yakuraion.androidcommon.coroutines.Dispatchers

/**
 * https://developer.android.com/kotlin/coroutines/test#setting-main-dispatcher
 */
@ExperimentalCoroutinesApi
class TestDispatchersRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {

    val dispatchers: Dispatchers = Dispatchers(
        ioDispatcher = testDispatcher,
        computeDispatcher = testDispatcher,
        mainDispatcher = testDispatcher,
        unconfinedDispatcher = testDispatcher,
    )

    override fun starting(description: Description) {
        kotlinx.coroutines.Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        kotlinx.coroutines.Dispatchers.resetMain()
    }
}
