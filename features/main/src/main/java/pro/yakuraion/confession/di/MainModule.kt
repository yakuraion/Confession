package pro.yakuraion.confession.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import pro.yakuraion.confession.home.HomeViewModel
import pro.yakuraion.confession.stacks.StacksViewModel

val mainModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::StacksViewModel)
}
