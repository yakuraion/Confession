package pro.yakuraion.parish.data.di.network

import org.koin.dsl.module

internal val networkModule = module {
    includes(fileDownloaderModule)
}
