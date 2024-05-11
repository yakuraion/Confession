package pro.yakuraion.confession.newconfession

import androidx.lifecycle.ViewModel
import java.time.LocalDate

class NewConfessionViewModel : ViewModel() {

    val state = NewConfessionState()

    fun onDateSelect(date: LocalDate) {
        state.date = date
    }

    fun onPakutaChanged(pakuta: String) {
        state.pakuta = pakuta
    }

    fun onCommentChanged(comment: String) {
        state.comment = comment
    }

    fun onContinueClick() {

    }
}
