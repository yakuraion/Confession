package pro.yakuraion.confession.home.components.nolastconfession

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeNoLastConfession(
    onCreateConfessionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        HomeCreateConfessionCard(
            onClick = onCreateConfessionClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
