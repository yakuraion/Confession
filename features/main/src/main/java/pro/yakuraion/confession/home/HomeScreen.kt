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
import pro.yakuraion.confession.commonui.compose.theme.AppTheme
import pro.yakuraion.confession.home.components.HomeLastConfessionCard
import pro.yakuraion.confession.home.components.HomePakutaCard
import pro.yakuraion.confession.home.components.HomeQuoteBlock
import pro.yakuraion.confession.home.components.HomeScreenTopBar
import java.time.LocalDate

@DestinationScreen
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    HomeScreen(
        state = viewModel.state,
        onCalendarClick = viewModel::onCalendarClick,
        onNotificationsClick = viewModel::onNotificationsClick,
    )
}

@Composable
private fun HomeScreen(
    state: HomeState,
    onCalendarClick: () -> Unit,
    onNotificationsClick: () -> Unit,
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

            when (state) {
                is HomeState.Content -> {
                    Content(state = state)
                }

                is HomeState.Loading -> Unit
            }
        }
    }
}

@Composable
private fun Content(
    state: HomeState.Content,
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
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        HomePakutaCard(
            pakutaText = "Вяночак да Божай міласэрнасці",
            checked = false,
            onCheckedChange = {},
            modifier = Modifier.fillMaxWidth()
        )
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
private fun Preview() {
    val state = HomeState.Content(
        now = LocalDate.of(2024, 5, 21),
        lastConfessionDate = LocalDate.of(2024, 4, 20),
    )
    AppTheme {
        HomeScreen(
            state = state,
            onCalendarClick = {},
            onNotificationsClick = {},
        )
    }
}
