package pro.yakuraion.confession.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import io.github.yakuraion.destinationscompose.core.DestinationScreen
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.confession.commonui.compose.theme.AppTheme
import pro.yakuraion.confession.home.components.HomeLastConfessionCard
import java.time.LocalDate

@DestinationScreen
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    HomeScreen(
        state = viewModel.state,
    )
}

@Composable
private fun HomeScreen(
    state: HomeState,
) {
    Scaffold(
        modifier = Modifier.drawBackground()
    ) { paddingValues ->
        val modifier = Modifier
            .fillMaxSize()
            .drawBackground()
            .padding(paddingValues)
        when (state) {
            is HomeState.Content -> {
                Content(
                    state = state,
                    modifier = modifier,
                )
            }

            is HomeState.Loading -> Unit
        }
    }
}

@Composable
private fun Content(
    state: HomeState.Content,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val (lastConfessionDateRef) = createRefs()

        HomeLastConfessionCard(
            now = state.now,
            lastDate = state.lastConfessionDate,
            modifier = Modifier.constrainAs(lastConfessionDateRef) {
                start.linkTo(parent.start, 20.dp)
                end.linkTo(parent.end, 20.dp)
                top.linkTo(parent.top, 146.dp)
                width = Dimension.fillToConstraints
            }
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
        )
    }
}
