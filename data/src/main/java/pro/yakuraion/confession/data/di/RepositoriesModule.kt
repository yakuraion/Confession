package pro.yakuraion.confession.data.di

import org.koin.core.module.Module
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
