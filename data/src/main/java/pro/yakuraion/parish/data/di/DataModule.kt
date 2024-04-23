package pro.yakuraion.parish.data.di

import com.google.gson.Gson
import org.koin.dsl.module
import pro.yakuraion.parish.data.di.network.networkModule
import pro.yakuraion.parish.data.di.persistence.persistenceModule

val dataModule = module {
    includes(repositoriesModule, persistenceModule, networkModule)

    single { Gson() }
}
