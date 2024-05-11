package pro.yakuraion.confession.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import pro.yakuraion.confession.home.HomeViewModel

val mainModule = module {
    viewModelOf(::HomeViewModel)
}
