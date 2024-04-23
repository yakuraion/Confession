package pro.yakuraion.parish.main.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import pro.yakuraion.parish.main.ui.MainViewModel

val mainModule = module {
    viewModelOf(::MainViewModel)
}
