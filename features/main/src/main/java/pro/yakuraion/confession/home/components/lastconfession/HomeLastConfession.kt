package pro.yakuraion.confession.home.components.lastconfession

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pro.yakuraion.confession.home.HomeState

@Composable
fun HomeLastConfession(
    state: HomeState.LastConfession,
    onLastConfessionClick: () -> Unit,
    onPakutaCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        HomeLastConfessionCard(
            now = state.now,
            lastDate = state.lastConfessionDate,
            onClick = onLastConfessionClick,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        HomePakutaCard(
            pakutaText = state.lastConfessionPakuta,
            checked = state.pakutaChecked,
            onCheckedChange = onPakutaCheckedChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
