package pro.yakuraion.confession.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import pro.yakuraion.confession.domain.usecases.GetLastConfessionUseCase
import pro.yakuraion.confession.domain.usecases.SetNewConfessionUseCase

internal val useCasesModule = module {
    factoryOf(::GetLastConfessionUseCase)
    factoryOf(::SetNewConfessionUseCase)
}
