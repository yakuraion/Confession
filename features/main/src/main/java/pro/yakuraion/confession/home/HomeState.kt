package pro.yakuraion.confession.home

import java.time.LocalDate

sealed class HomeState {

    data object Loading : HomeState()

    data object NoLastConfession : HomeState()

    data class LastConfession(
        val now: LocalDate,
        val lastConfessionDate: LocalDate,
        val lastConfessionPakuta: String,
        val pakutaChecked: Boolean,
    ) : HomeState()
}
