package pro.yakuraion.parish.domain.utils.localdate

import java.time.LocalDate

class LocalDateProviderImpl : LocalDateProvider {

    override fun now(): LocalDate = LocalDate.now()
}
