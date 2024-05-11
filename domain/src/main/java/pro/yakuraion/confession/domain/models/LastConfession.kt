package pro.yakuraion.confession.domain.models

import java.time.LocalDate

data class LastConfession(
    val date: LocalDate,
    val pakuta: String,
    val comment: String,
)
