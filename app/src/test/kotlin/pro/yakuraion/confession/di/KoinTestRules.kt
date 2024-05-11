package pro.yakuraion.confession.di

import android.content.Context
import org.koin.core.module.Module
import org.koin.test.KoinTestRule
import pro.yakuraion.androidcommon.coroutines.Dispatchers
import pro.yakuraion.confession.di.data.getFakeServerDataModules
import pro.yakuraion.confession.di.data.getRealServerDataModules
import pro.yakuraion.confession.domain.di.domainModule

fun createDataDomainKoinTestRule(
    context: Context,
    dispatchers: Dispatchers,
    isRealServer: Boolean = false,
    vararg customModules: Module,
): KoinTestRule = KoinTestRule.create {
    modules(
        if (isRealServer) {
            getRealServerDataModules(context, dispatchers)
        } else {
            getFakeServerDataModules(context, dispatchers)
        }
    )
    modules(domainModule)
    modules(*customModules)
}
