package pro.yakuraion.confession.newconfession

sealed class NewConfessionDialogsState {

    data object None : NewConfessionDialogsState()

    data object DatePicker : NewConfessionDialogsState()
}
