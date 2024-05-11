package pro.yakuraion.confession.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.yakuraion.destinationscompose.core.DestinationScreen
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.confession.commonui.compose.coroutines.collectInLaunchedEffect
import pro.yakuraion.confession.commonui.compose.theme.AppTheme
import pro.yakuraion.confession.home.components.HomeQuoteBlock
import pro.yakuraion.confession.home.components.HomeScreenTopBar
import pro.yakuraion.confession.home.components.lastconfession.HomeLastConfession
import pro.yakuraion.confession.home.components.nolastconfession.HomeNoLastConfession
import java.time.LocalDate

@DestinationScreen
@Composable
fun HomeScreen(
    onOpenCreateConfessionRequest: () -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {
    viewModel.onOpenCreateConfessionRequest.collectInLaunchedEffect {
        onOpenCreateConfessionRequest.invoke()
    }

    HomeScreen(
        state = viewModel.state,
        onCalendarClick = viewModel::onCalendarClick,
        onNotificationsClick = viewModel::onNotificationsClick,
        onCreateConfessionClick = viewModel::onCreateConfessionClick,
        onLastConfessionClick = viewModel::onLastConfessionClick,
    )
}

@Composable
private fun HomeScreen(
    state: HomeState,
    onCalendarClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onCreateConfessionClick: () -> Unit,
    onLastConfessionClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBackground()
    ) {
        HomeQuoteBlock(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )

        Column {
            val topPadding = with(LocalDensity.current) {
                WindowInsets.systemBars.getTop(this).toDp()
            }
            HomeScreenTopBar(
                onCalendarClick = onCalendarClick,
                onNotificationsClick = onNotificationsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = topPadding)
            )

            Spacer(modifier = Modifier.height(20.dp))

            when (state) {
                is HomeState.Loading -> Unit

                is HomeState.NoLastConfession -> {
                    HomeNoLastConfession(
                        onCreateConfessionClick = onCreateConfessionClick,
                    )
                }

                is HomeState.LastConfession -> {
                    HomeLastConfession(
                        state = state,
                        onLastConfessionClick = onLastConfessionClick,
                    )
                }
            }
        }
    }
}

private fun Modifier.drawBackground(): Modifier {
    return drawBehind {
        drawRect(
            backgroundBrush(
                start = Offset(0f, 0f),
                end = Offset(size.width, size.height - 200f)
            ),
            size = size,
        )
    }
}

private fun backgroundBrush(
    start: Offset,
    end: Offset,
): Brush {
    return Brush.linearGradient(
        0f to Color(0xFF5D03CF),
        0.39f to Color(0xFF7400E9),
        0.68f to Color(0xFF9D20FF),
        0.88f to Color(0xFFEC79FF),
        1f to Color(0xFFFF98E3),
        start = start,
        end = end,
    )
}

@Preview
@Composable
private fun PreviewLastConfession() {
    val state = HomeState.LastConfession(
        now = LocalDate.of(2024, 5, 21),
        lastConfessionDate = LocalDate.of(2024, 4, 20),
        lastConfessionPakuta = "Вячочак до Божай Миласернасци"
    )
    AppTheme {
        HomeScreen(
            state = state,
            onCalendarClick = {},
            onNotificationsClick = {},
            onCreateConfessionClick = {},
            onLastConfessionClick = {},
        )
    }
}

@Preview
@Composable
private fun PreviewNoLastConfession() {
    val state = HomeState.NoLastConfession
    AppTheme {
        HomeScreen(
            state = state,
            onCalendarClick = {},
            onNotificationsClick = {},
            onCreateConfessionClick = {},
            onLastConfessionClick = {},
        )
    }
}
