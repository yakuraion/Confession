package pro.yakuraion.parish.commonui.compose.coroutines

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@SuppressLint("ComposableNaming")
@Composable
fun <T> Flow<T>.collectInLaunchedEffect(key: Any? = Unit, action: (T) -> Unit) {
    LaunchedEffect(key) {
        collect { action(it) }
    }
}
