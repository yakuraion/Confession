package pro.yakuraion.confession.debugging.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import pro.yakuraion.confession.debugging.ui.DebuggingViewModel

val debuggingModule = module {
    viewModelOf(::DebuggingViewModel)
}
