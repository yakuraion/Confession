package pro.yakuraion.confession.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import pro.yakuraion.confession.confession.ConfessionViewModel
import pro.yakuraion.confession.home.HomeViewModel
import pro.yakuraion.confession.newconfession.NewConfessionViewModel
import pro.yakuraion.confession.stacks.StacksViewModel

val mainModule = module {
    viewModelOf(::StacksViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::NewConfessionViewModel)
    viewModelOf(::ConfessionViewModel)
}
