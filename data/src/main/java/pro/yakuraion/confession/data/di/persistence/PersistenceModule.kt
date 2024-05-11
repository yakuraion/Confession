package pro.yakuraion.confession.data.di.persistence

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pro.yakuraion.confession.data.persistence.typeconverters.LocalDateTypeConverter

internal val persistenceModule = module {
    includes(databaseModule, preferencesModule)

    factoryOf(::LocalDateTypeConverter)
}
