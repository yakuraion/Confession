package pro.yakuraion.confession.newconfession

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pro.yakuraion.confession.domain.usecases.SetNewConfessionUseCase
import java.time.LocalDate

class NewConfessionViewModel(
    private val setNewConfessionUseCase: SetNewConfessionUseCase,
) : ViewModel() {

    private val _onSuccess: Channel<Unit> = Channel(Channel.BUFFERED)
    val onSuccess: Flow<Unit> = _onSuccess.receiveAsFlow()

    val state = NewConfessionState()

    var dialogsState: NewConfessionDialogsState by mutableStateOf(NewConfessionDialogsState.None)
        private set

    fun onDateClick() {
        dialogsState = NewConfessionDialogsState.DatePicker
    }

    fun onPakutaChange(pakuta: String) {
        state.pakuta = pakuta
        updateIsContinueEnabled()
    }

    fun onCommentChange(comment: String) {
        state.comment = comment
    }

    fun onContinueClick() {
        val date = state.date ?: return
        if (state.pakuta.isBlank()) return

        state.isContinueLoading = true
        viewModelScope.launch {
            try {
                setNewConfessionUseCase.invoke(
                    date = date,
                    pakuta = state.pakuta,
                    comment = state.comment,
                )
                _onSuccess.trySend(Unit)
            } finally {
                state.isContinueLoading = false
            }
        }
    }

    fun onDatePickerDialogDismissRequest() {
        dialogsState = NewConfessionDialogsState.None
    }

    fun onDatePickerDialogDateSelect(date: LocalDate) {
        state.date = date
        dialogsState = NewConfessionDialogsState.None
        updateIsContinueEnabled()
    }

    private fun updateIsContinueEnabled() {
        val isEnabled = state.date != null && state.pakuta.isNotBlank()
        state.isContinueEnabled = isEnabled
    }
}
