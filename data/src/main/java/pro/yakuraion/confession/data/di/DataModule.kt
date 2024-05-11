package pro.yakuraion.confession.data.di

import com.google.gson.Gson
import org.koin.dsl.module
import pro.yakuraion.confession.data.di.network.networkModule
import pro.yakuraion.confession.data.di.persistence.persistenceModule

val dataModule = module {
    includes(repositoriesModule, persistenceModule, networkModule)

    single { Gson() }
}
