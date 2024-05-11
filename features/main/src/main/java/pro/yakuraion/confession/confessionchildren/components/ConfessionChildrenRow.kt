package pro.yakuraion.confession.confessionchildren.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pro.yakuraion.confession.confessionchildren.models.ConfessionChildrenItem

@Composable
fun ConfessionChildrenRow(
    item: ConfessionChildrenItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(20.dp)
    Box(
        modifier = modifier
            .height(62.dp)
            .fillMaxWidth()
            .clip(shape)
            .drawBackground()
            .clickable { onClick.invoke() }
            .padding(start = 30.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            text = stringResource(id = item.titleRes),
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

private fun Modifier.drawBackground(): Modifier {
    return drawBehind {
        drawRect(
            backgroundBrush(
                start = Offset(size.width * 1.5f, 0f),
                end = Offset(0f, 0f)
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
        0f to Color(0xFFC1ABFF),
        1f to Color(0xFF8E3BEF),
        start = start,
        end = end,
    )
}
