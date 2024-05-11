package pro.yakuraion.confession.domain.utils.localdate

import java.time.LocalDate

interface LocalDateProvider {

    fun now(): LocalDate
}
