package pro.yakuraion.confession.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pro.yakuraion.confession.domain.usecases.GetLastConfessionUseCase
import java.time.LocalDate

class HomeViewModel(
    private val getLastConfessionUseCase: GetLastConfessionUseCase,
) : ViewModel() {

    private val _onOpenCreateConfessionRequest: Channel<Unit> = Channel(Channel.BUFFERED)
    val onOpenCreateConfessionRequest: Flow<Unit> = _onOpenCreateConfessionRequest.receiveAsFlow()

    var state: HomeState by mutableStateOf(HomeState.Loading)
        private set

    init {
        collectLastConfessions()
    }

    private fun collectLastConfessions() {
        viewModelScope.launch {
            getLastConfessionUseCase.invoke().collect { lastConfession ->
                state = if (lastConfession == null) {
                    HomeState.NoLastConfession
                } else {
                    HomeState.LastConfession(
                        now = LocalDate.now(),
                        lastConfessionDate = lastConfession.date,
                        lastConfessionPakuta = lastConfession.pakuta,
                    )
                }
            }
        }
    }

    fun onCalendarClick() {

    }

    fun onNotificationsClick() {

    }

    fun onCreateConfessionClick() {
        _onOpenCreateConfessionRequest.trySend(Unit)
    }
}
