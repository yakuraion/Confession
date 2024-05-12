package pro.yakuraion.confession.sins

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pro.yakuraion.confession.domain.usecases.GetSinTopicsUseCase

class SinsViewModel(
    private val getSinTopicsUseCase: GetSinTopicsUseCase,
) : ViewModel() {

    var state: SinsState by mutableStateOf(SinsState.Loading)
        private set

    init {
        viewModelScope.launch {
            state = SinsState.Content(getSinTopicsUseCase.invoke())
        }
    }
}
