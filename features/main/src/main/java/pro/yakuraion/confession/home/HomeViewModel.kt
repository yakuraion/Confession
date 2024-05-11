package pro.yakuraion.confession.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class HomeViewModel : ViewModel() {

    var state: HomeState by mutableStateOf(HomeState.Loading)
        private set

    init {
        state = HomeState.Content(LocalDate.now(), LocalDate.now())
    }
}
