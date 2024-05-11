package pro.yakuraion.confession.stacks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.yakuraion.destinationscompose.core.DestinationScreen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.confession.commonui.compose.theme.AppTheme
import pro.yakuraion.confession.confession.ConfessionScreen
import pro.yakuraion.confession.home.HomeScreen

@DestinationScreen
@Composable
fun StacksScreen(
    onOpenCreateConfessionRequest: () -> Unit,
    viewModel: StacksViewModel = koinViewModel(),
) {
    StacksScreen(
        onHomeOpenCreateConfessionRequest = onOpenCreateConfessionRequest,
    )
}

@Composable
private fun StacksScreen(
    onHomeOpenCreateConfessionRequest: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        val pagerState = rememberPagerState { StacksPage.entries.count() }
        Pager(
            state = pagerState,
            onHomeOpenCreateConfessionRequest = onHomeOpenCreateConfessionRequest,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )

        val bottomNavigationCoroutineScope = rememberCoroutineScope()
        BottomNavigationBar(
            selectedPage = StacksPage.entries[pagerState.currentPage],
            onPageClick = { page ->
                bottomNavigationCoroutineScope.launch { pagerState.scrollToPage(page.ordinal) }
            }
        )
    }
}

@Composable
private fun BottomNavigationBar(
    selectedPage: StacksPage,
    onPageClick: (StacksPage) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.White,
        contentColor = Color.Black,
    ) {
        StacksPage.entries.forEach { page ->
            NavigationBarItem(
                selected = selectedPage == page,
                onClick = { onPageClick.invoke(page) },
                icon = {
                    Icon(
                        painter = painterResource(page.iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(page.labelRes),
                        style = MaterialTheme.typography.bodySmall,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.Black,
                    indicatorColor = Color(0xFFEC79FF),
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Black,
                )
            )
        }
    }
}

@Composable
private fun Pager(
    state: PagerState,
    onHomeOpenCreateConfessionRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(
        state = state,
        modifier = modifier,
        userScrollEnabled = false,
    ) { page ->
        when (page) {
            StacksPage.HOME.ordinal -> HomeScreen(
                onOpenCreateConfessionRequest = onHomeOpenCreateConfessionRequest,
            )

            StacksPage.CONFESSION.ordinal -> ConfessionScreen()

            else -> Unit
        }
    }
}

@Preview
@Composable
private fun PreviewBottomNavigation() {
    AppTheme {
        BottomNavigationBar(
            selectedPage = StacksPage.HOME,
            onPageClick = {}
        )
    }
}
