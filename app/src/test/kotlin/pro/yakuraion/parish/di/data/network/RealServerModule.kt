package pro.yakuraion.parish.di.data.network

import org.koin.dsl.module
import pro.yakuraion.parish.data.DataConfiguration
import java.util.Properties

val realServerModule = module {
    single {
        val inputStream = this.javaClass.classLoader?.getResourceAsStream("custom.properties")
        val properties = Properties().apply { load(inputStream) }
        DataConfiguration(
            baseUrl = properties.getProperty("base_url"),
        )
    }
}
