package pro.yakuraion.confession.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pro.yakuraion.confession.domain.usecases.GetLastConfessionUseCase
import pro.yakuraion.confession.domain.usecases.GetPakutaCheckedUseCase
import pro.yakuraion.confession.domain.usecases.SetPakutaCheckedUseCase
import java.time.LocalDate

class HomeViewModel(
    private val getLastConfessionUseCase: GetLastConfessionUseCase,
    private val getPakutaCheckedUseCase: GetPakutaCheckedUseCase,
    private val setPakutaCheckedUseCase: SetPakutaCheckedUseCase,
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
            combine(
                getLastConfessionUseCase.invoke(),
                getPakutaCheckedUseCase.invoke()
            ) { lastConfession, pakutaChecked ->

                state = if (lastConfession == null) {
                    HomeState.NoLastConfession
                } else {
                    HomeState.LastConfession(
                        now = LocalDate.now(),
                        lastConfessionDate = lastConfession.date,
                        lastConfessionPakuta = lastConfession.pakuta,
                        pakutaChecked = pakutaChecked
                    )
                }
            }.collect {}
        }
    }

    fun onCalendarClick() {

    }

    fun onNotificationsClick() {

    }

    fun onCreateConfessionClick() {
        _onOpenCreateConfessionRequest.trySend(Unit)
    }

    fun onLastConfessionClick() {
        _onOpenCreateConfessionRequest.trySend(Unit)
    }

    fun onPakutaCheckedChange(isChecked: Boolean) {
        viewModelScope.launch {
            setPakutaCheckedUseCase.invoke(isChecked)
        }
    }
}
