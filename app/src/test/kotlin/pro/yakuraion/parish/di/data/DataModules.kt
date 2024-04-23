package pro.yakuraion.parish.di.data

import android.content.Context
import org.koin.core.module.Module
import org.koin.dsl.module
import pro.yakuraion.androidcommon.coroutines.Dispatchers
import pro.yakuraion.parish.data.di.dataModule
import pro.yakuraion.parish.di.data.network.fakeServerModule
import pro.yakuraion.parish.di.data.network.realServerModule
import pro.yakuraion.parish.di.data.persistence.fakePersistenceModule

fun getRealServerDataModules(
    context: Context,
    dispatchers: Dispatchers,
): List<Module> = getSharedModules(context, dispatchers) + realServerModule

fun getFakeServerDataModules(
    context: Context,
    dispatchers: Dispatchers,
): List<Module> = getSharedModules(context, dispatchers) + fakeServerModule

private fun getSharedModules(context: Context, dispatchers: Dispatchers): List<Module> {
    val contextModule = module { single { context } }
    val dispatchersModule = module { single { dispatchers } }
    return listOf(contextModule, dispatchersModule, dataModule, fakePersistenceModule)
}
