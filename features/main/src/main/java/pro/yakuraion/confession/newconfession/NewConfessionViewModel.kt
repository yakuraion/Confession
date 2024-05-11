package pro.yakuraion.confession.newconfession

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class NewConfessionViewModel : ViewModel() {

    val state = NewConfessionState()

    var dialogsState: NewConfessionDialogsState by mutableStateOf(NewConfessionDialogsState.None)
        private set

    fun onDateClick() {
        dialogsState = NewConfessionDialogsState.DatePicker
    }

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

    fun onDatePickerDialogDismissRequest() {
        dialogsState = NewConfessionDialogsState.None
    }

    fun onDatePickerDialogDateSelect(date: LocalDate) {
        state.date = date
        dialogsState = NewConfessionDialogsState.None
    }
}
