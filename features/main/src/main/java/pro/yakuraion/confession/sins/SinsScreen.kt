package pro.yakuraion.confession.sins

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.yakuraion.destinationscompose.core.DestinationScreen
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.confession.common.NavigationIconBack
import pro.yakuraion.confession.common.TitleText
import pro.yakuraion.confession.commonui.R
import pro.yakuraion.confession.commonui.compose.theme.AppTheme
import pro.yakuraion.confession.sins.components.SinsList

@DestinationScreen
@Composable
fun SinsScreen(
    onBackRequest: () -> Unit,
    viewModel: SinsViewModel = koinViewModel(),
) {
    SinsScreen(
        onBackClick = onBackRequest,
    )
}

@Composable
private fun SinsScreen(
    onBackClick: () -> Unit,
) {
    val topPadding = with(LocalDensity.current) {
        WindowInsets.systemBars.getTop(this).toDp()
    }
    val bottomPadding = with(LocalDensity.current) {
        WindowInsets.systemBars.getBottom(this).toDp()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawBackground()
            .padding(top = topPadding)
    ) {
        TopAppBar(
            onBackClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        TitleText(
            text = stringResource(id = R.string.sins_title),
            modifier = Modifier.padding(start = 40.dp)
        )

        SinsList()
    }
}

@Composable
private fun TopAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.padding(start = 20.dp, top = 20.dp)) {
        NavigationIconBack(onClick = onBackClick)
    }
}

private fun Modifier.drawBackground(): Modifier {
    return drawBehind {
        drawRect(
            backgroundBrush(
                start = Offset(size.width, 0f),
                end = Offset(0f, size.height)
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
        0f to Color(0xFFE6A0FF),
        0.76f to Color(0xFF5D03CF),
        start = start,
        end = end,
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        SinsScreen(
            onBackClick = {},
        )
    }
}
