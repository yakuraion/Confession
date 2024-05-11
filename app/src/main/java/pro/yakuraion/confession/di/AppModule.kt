package pro.yakuraion.confession.di

import org.koin.core.module.Module
import org.koin.dsl.module
import pro.yakuraion.androidcommon.coroutines.Dispatchers
import pro.yakuraion.confession.BuildConfig
import pro.yakuraion.confession.data.DataConfiguration
import pro.yakuraion.confession.data.di.dataModule
import pro.yakuraion.confession.debugging.di.debuggingModule
import pro.yakuraion.confession.domain.di.domainModule
import pro.yakuraion.confession.main.di.mainModule

val appModule = module {
    installDispatchers()

    single { DataConfiguration(baseUrl = BuildConfig.API_BASE_URL) }

    includes(dataModule, domainModule)

    includes(
        mainModule,
        debuggingModule,
    )
}

private fun Module.installDispatchers() {
    single { Dispatchers.original() }
}
