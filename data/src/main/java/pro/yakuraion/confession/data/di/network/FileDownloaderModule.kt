package pro.yakuraion.confession.data.di.network

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pro.yakuraion.confession.data.network.filedownload.FileDownloader
import pro.yakuraion.confession.data.network.filedownload.RealFileDownloader

internal val fileDownloaderModule = module {
    singleOf(::RealFileDownloader) bind FileDownloader::class
}
