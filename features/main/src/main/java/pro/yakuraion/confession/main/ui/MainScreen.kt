package pro.yakuraion.confession.main.ui

import android.content.res.Configuration
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.confession.commonui.compose.theme.AppTheme

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel(),
) {
    MainScreen()
}

@Composable
internal fun MainScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "",
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    AppTheme {
        MainScreen()
    }
}
