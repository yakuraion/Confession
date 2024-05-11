package pro.yakuraion.confession.newconfession

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate

class NewConfessionState {

    var date: LocalDate? by mutableStateOf(null)

    var pakuta: String by mutableStateOf("")

    var comment: String by mutableStateOf("")

    var isContinueEnabled: Boolean by mutableStateOf(false)

    var isContinueLoading: Boolean by mutableStateOf(false)
}
