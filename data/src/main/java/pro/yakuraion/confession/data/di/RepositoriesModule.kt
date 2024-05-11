package pro.yakuraion.confession.data.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pro.yakuraion.confession.data.repositories.LastConfessionRepositoryImpl
import pro.yakuraion.confession.data.repositories.converters.LastConfessionConverter
import pro.yakuraion.confession.domain.repositories.LastConfessionRepository

internal val repositoriesModule = module {
    installDataSources()
    installRepositories()
    installConverters()
}

private fun Module.installDataSources() {
}

private fun Module.installRepositories() {
    singleOf(::LastConfessionRepositoryImpl) bind LastConfessionRepository::class
}

private fun Module.installConverters() {
    singleOf(::LastConfessionConverter)
}
