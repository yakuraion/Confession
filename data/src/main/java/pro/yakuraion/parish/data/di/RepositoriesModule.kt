package pro.yakuraion.parish.data.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

internal val repositoriesModule = module {
    installDataSources()
    installRepositories()
    installConverters()
}

private fun Module.installDataSources() {
}

private fun Module.installRepositories() {
}

private fun Module.installConverters() {
}
