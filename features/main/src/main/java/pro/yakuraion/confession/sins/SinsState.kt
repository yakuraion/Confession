package pro.yakuraion.confession.sins

import pro.yakuraion.confession.domain.models.SinTopic

sealed class SinsState {

    data object Loading : SinsState()

    data class Content(
        val topics: List<SinTopic>,
    ) : SinsState()
}
