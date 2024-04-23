package pro.yakuraion.parish.domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pro.yakuraion.parish.domain.utils.localdate.LocalDateProvider
import pro.yakuraion.parish.domain.utils.localdate.LocalDateProviderImpl

val domainModule = module {
    includes(useCasesModule)

    singleOf(::LocalDateProviderImpl) bind LocalDateProvider::class
}
