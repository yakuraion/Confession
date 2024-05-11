package pro.yakuraion.confession.main.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class MainViewModel : ViewModel() {

    private val _navigationCommands: Channel<MainNavigationCommand> = Channel(Channel.BUFFERED)
    val navigationCommands: Flow<MainNavigationCommand> = _navigationCommands.receiveAsFlow()
}
