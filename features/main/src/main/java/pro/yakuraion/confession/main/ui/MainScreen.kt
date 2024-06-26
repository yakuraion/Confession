package pro.yakuraion.confession.main.ui

import android.content.res.Configuration
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.confession.commonui.compose.coroutines.collectInLaunchedEffect
import pro.yakuraion.confession.commonui.compose.theme.AppTheme
import pro.yakuraion.confession.newconfession.navigateToNewConfessionScreen
import pro.yakuraion.confession.newconfession.newConfessionScreenComposable
import pro.yakuraion.confession.sins.navigateToSinsScreen
import pro.yakuraion.confession.sins.sinsScreenComposable
import pro.yakuraion.confession.stacks.getStacksScreenRouteScheme
import pro.yakuraion.confession.stacks.navigateToStacksScreen
import pro.yakuraion.confession.stacks.stacksScreenComposable

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel(),
) {
    MainScreen(
        navigationCommands = viewModel.navigationCommands,
        onStacksOpenCreateConfessionRequest = viewModel::onStacksOpenCreateConfessionRequest,
        onStacksOpenSinsRequest = viewModel::onStacksOpenSinsRequest,
        onNewConfessionBackRequest = viewModel::onNewConfessionBackRequest,
        onNewConfessionSuccess = viewModel::onNewConfessionSuccess,
        onSinsBackRequest = viewModel::onSinsBackRequest,
    )
}

@Composable
internal fun MainScreen(
    navigationCommands: Flow<MainNavigationCommand>,
    onStacksOpenCreateConfessionRequest: () -> Unit,
    onStacksOpenSinsRequest: () -> Unit,
    onNewConfessionBackRequest: () -> Unit,
    onNewConfessionSuccess: () -> Unit,
    onSinsBackRequest: () -> Unit,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = getStacksScreenRouteScheme(),
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        stacksScreenComposable(
            onOpenCreateConfessionRequest = onStacksOpenCreateConfessionRequest,
            onOpenSinsRequest = onStacksOpenSinsRequest,
        )
        newConfessionScreenComposable(
            onBackRequest = onNewConfessionBackRequest,
            onSuccess = onNewConfessionSuccess,
        )
        sinsScreenComposable(
            onBackRequest = onSinsBackRequest,
        )
    }

    navigationCommands.collectInLaunchedEffect { command ->
        when (command) {
            is MainNavigationCommand.NavigateBack -> {
                navController.popBackStack()
            }

            is MainNavigationCommand.NavigateToStacks -> {
                navController.navigateToStacksScreen()
            }

            is MainNavigationCommand.NavigateToNewConfession -> {
                navController.navigateToNewConfessionScreen()
            }

            is MainNavigationCommand.NavigateToSins -> {
                navController.navigateToSinsScreen()
            }
        }
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
