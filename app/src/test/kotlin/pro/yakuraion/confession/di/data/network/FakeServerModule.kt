package pro.yakuraion.confession.di.data.network

import okhttp3.mockwebserver.MockWebServer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pro.yakuraion.confession.data.DataConfiguration
import pro.yakuraion.confession.data.network.filedownload.FileDownloader
import pro.yakuraion.confession.network.filedownload.FakeFileDownloader

val fakeServerModule = module {
    single { MockWebServer() }

    single {
        DataConfiguration(
            baseUrl = get<MockWebServer>().url("/").toString(),
        )
    }

    singleOf(::FakeFileDownloader) bind FileDownloader::class
}
