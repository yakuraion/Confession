package pro.yakuraion.confession.main.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import pro.yakuraion.confession.main.ui.MainViewModel

val mainModule = module {
    viewModelOf(::MainViewModel)
}
