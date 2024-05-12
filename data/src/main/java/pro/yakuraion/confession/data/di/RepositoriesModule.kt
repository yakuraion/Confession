package pro.yakuraion.confession.data.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pro.yakuraion.confession.data.repositories.LastConfessionRepositoryImpl
import pro.yakuraion.confession.data.repositories.SinTopicsRepositoryImpl
import pro.yakuraion.confession.data.repositories.converters.LastConfessionConverter
import pro.yakuraion.confession.data.repositories.converters.SinTopicConverter
import pro.yakuraion.confession.data.repositories.datasources.SinTopicsDataSource
import pro.yakuraion.confession.domain.repositories.LastConfessionRepository
import pro.yakuraion.confession.domain.repositories.SinTopicsRepository

internal val repositoriesModule = module {
    installDataSources()
    installRepositories()
    installConverters()
}

private fun Module.installDataSources() {
    singleOf(::SinTopicsDataSource)
}

private fun Module.installRepositories() {
    singleOf(::LastConfessionRepositoryImpl) bind LastConfessionRepository::class
    singleOf(::SinTopicsRepositoryImpl) bind SinTopicsRepository::class
}

private fun Module.installConverters() {
    singleOf(::LastConfessionConverter)
    singleOf(::SinTopicConverter)
}
