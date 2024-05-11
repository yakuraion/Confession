package pro.yakuraion.confession.utils

import pro.yakuraion.confession.domain.utils.localdate.LocalDateProvider
import java.time.LocalDate

class TestLocalDateProvider : LocalDateProvider {

    private var now: LocalDate = LocalDate.now()

    override fun now(): LocalDate = now

    fun setNow(localDate: LocalDate) {
        now = localDate
    }
}
