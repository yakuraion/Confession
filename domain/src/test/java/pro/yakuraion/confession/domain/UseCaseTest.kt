package pro.yakuraion.confession.domain

import org.junit.Before
import org.junit.Rule
import pro.yakuraion.androidcommon.coroutinestests.TestDispatchersRule

abstract class UseCaseTest<T : Any> {

    @get:Rule
    val testDispatchersRule = TestDispatchersRule()

    lateinit var useCase: T

    abstract fun setUpMocks()

    abstract fun createUseCase(): T

    @Before
    open fun setUp() {
        setUpMocks()
        useCase = createUseCase()
    }
}
