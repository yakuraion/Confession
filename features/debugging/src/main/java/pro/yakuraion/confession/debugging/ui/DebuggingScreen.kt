package pro.yakuraion.confession.debugging.ui

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.confession.commonui.compose.theme.AppTheme

@Composable
fun DebuggingScreen(
    onBackRequest: () -> Unit,
    viewModel: DebuggingViewModel = koinViewModel(),
) {
    BackHandler { onBackRequest() }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DebuggingScreenPreview() {
    AppTheme {
        DebuggingScreen(
            onBackRequest = {},
        )
    }
}
