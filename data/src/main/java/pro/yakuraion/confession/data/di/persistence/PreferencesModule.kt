package pro.yakuraion.confession.data.di.persistence

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pro.yakuraion.confession.data.persistence.preferences.LastConfessionPreferences

internal val preferencesModule = module {
    singleOf(::LastConfessionPreferences)
}
