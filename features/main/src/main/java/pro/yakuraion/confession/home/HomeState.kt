package pro.yakuraion.confession.home

import java.time.LocalDate

sealed class HomeState {

    data object Loading : HomeState()

    data class Content(
        val now: LocalDate,
        val lastConfessionDate: LocalDate,
    ) : HomeState()
}
