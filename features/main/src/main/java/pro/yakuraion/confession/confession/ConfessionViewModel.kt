package pro.yakuraion.confession.confession

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import pro.yakuraion.confession.confession.models.ConfessionItem

class ConfessionViewModel : ViewModel() {

    private val _onOpenSinsRequest: Channel<Unit> = Channel(Channel.BUFFERED)
    val onOpenSinsRequest: Flow<Unit> = _onOpenSinsRequest.receiveAsFlow()

    fun onConfessionItemClick(confessionItem: ConfessionItem) {
        if (confessionItem == ConfessionItem.SINS) {
            _onOpenSinsRequest.trySend(Unit)
        }
    }
}
