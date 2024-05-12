package pro.yakuraion.confession.confession

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.confession.common.CurveTopBar
import pro.yakuraion.confession.commonui.R
import pro.yakuraion.confession.commonui.compose.coroutines.collectInLaunchedEffect
import pro.yakuraion.confession.commonui.compose.theme.AppTheme
import pro.yakuraion.confession.confession.components.ConfessionRow
import pro.yakuraion.confession.confession.models.ConfessionItem

@Composable
fun ConfessionScreen(
    onOpenSinsRequest: () -> Unit,
    viewModel: ConfessionViewModel = koinViewModel(),
) {
    viewModel.onOpenSinsRequest.collectInLaunchedEffect {
        onOpenSinsRequest.invoke()
    }

    ConfessionScreen(
        onConfessionItemClick = viewModel::onConfessionItemClick,
    )
}

@Composable
private fun ConfessionScreen(
    onConfessionItemClick: (ConfessionItem) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawBackground()
    ) {
        CurveTopBar(
            title = stringResource(id = R.string.confession_title),
            subtitle = stringResource(id = R.string.confession_subtitle),
            bigImageResId = R.drawable.il_confession_big,
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column {
            ConfessionItem.entries.forEach { item ->
                ConfessionRow(
                    item = item,
                    onClick = { onConfessionItemClick.invoke(item) },
                    modifier = Modifier.padding(horizontal = 20.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

private fun Modifier.drawBackground(): Modifier {
    return drawBehind {
        drawRect(
            backgroundBrush(
                start = Offset(0f, size.height),
                end = Offset(size.width, 400f)
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
        0f to Color(0xFFEBDCFF),
        1f to Color(0xFFFFFFFF),
        start = start,
        end = end,
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        ConfessionScreen(
            onConfessionItemClick = {},
        )
    }
}
