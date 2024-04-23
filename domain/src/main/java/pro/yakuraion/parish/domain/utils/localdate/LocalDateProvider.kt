package pro.yakuraion.parish.domain.utils.localdate

import java.time.LocalDate

interface LocalDateProvider {

    fun now(): LocalDate
}
