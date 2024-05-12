package pro.yakuraion.confession.main.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class MainViewModel : ViewModel() {

    private val _navigationCommands: Channel<MainNavigationCommand> = Channel(Channel.BUFFERED)
    val navigationCommands: Flow<MainNavigationCommand> = _navigationCommands.receiveAsFlow()

    fun onStacksOpenCreateConfessionRequest() {
        _navigationCommands.trySend(MainNavigationCommand.NavigateToNewConfession)
    }

    fun onStacksOpenSinsRequest() {
        _navigationCommands.trySend(MainNavigationCommand.NavigateToSins)
    }

    fun onNewConfessionBackRequest() {
        _navigationCommands.trySend(MainNavigationCommand.NavigateBack)
    }

    fun onNewConfessionSuccess() {
        _navigationCommands.trySend(MainNavigationCommand.NavigateBack)
    }

    fun onSinsBackRequest() {
        _navigationCommands.trySend(MainNavigationCommand.NavigateBack)
    }
}
