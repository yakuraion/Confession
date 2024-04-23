package pro.yakuraion.parish.di

import android.content.Context
import org.koin.core.module.Module
import org.koin.test.KoinTestRule
import pro.yakuraion.androidcommon.coroutines.Dispatchers
import pro.yakuraion.parish.di.data.getFakeServerDataModules
import pro.yakuraion.parish.di.data.getRealServerDataModules
import pro.yakuraion.parish.domain.di.domainModule

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
