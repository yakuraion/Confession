package pro.yakuraion.parish.debugging.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import pro.yakuraion.parish.debugging.ui.DebuggingViewModel

val debuggingModule = module {
    viewModelOf(::DebuggingViewModel)
}
