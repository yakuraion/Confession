package pro.yakuraion.confession.topics

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pro.yakuraion.confession.domain.models.Topic
import pro.yakuraion.confession.domain.usecases.GetTopicsUseCase

class TopicsViewModel(
    private val getTopicsUseCase: GetTopicsUseCase,
) : ViewModel() {

    var topics: List<Topic> by mutableStateOf(emptyList())
        private set

    init {
        viewModelScope.launch {
            topics = getTopicsUseCase.invoke()
        }
    }
}
