package pro.yakuraion.parish.di

import org.koin.core.module.Module
import org.koin.dsl.module
import pro.yakuraion.androidcommon.coroutines.Dispatchers
import pro.yakuraion.parish.BuildConfig
import pro.yakuraion.parish.data.DataConfiguration
import pro.yakuraion.parish.data.di.dataModule
import pro.yakuraion.parish.debugging.di.debuggingModule
import pro.yakuraion.parish.domain.di.domainModule
import pro.yakuraion.parish.main.di.mainModule
import pro.yakuraion.parish.parishes.di.parishesModule

val appModule = module {
    installDispatchers()

    single { DataConfiguration(baseUrl = BuildConfig.API_BASE_URL) }

    includes(dataModule, domainModule)

    includes(
        mainModule,
        debuggingModule,
        parishesModule
    )
}

private fun Module.installDispatchers() {
    single { Dispatchers.original() }
}
