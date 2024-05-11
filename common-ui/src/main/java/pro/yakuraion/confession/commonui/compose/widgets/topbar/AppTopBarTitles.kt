package pro.yakuraion.confession.commonui.compose.widgets.topbar

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AppTopBarTitleText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
    )
}
