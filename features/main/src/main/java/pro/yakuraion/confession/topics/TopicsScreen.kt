package pro.yakuraion.confession.topics

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.yakuraion.destinationscompose.core.DestinationScreen
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.confession.common.CurveTopBar
import pro.yakuraion.confession.commonui.R
import pro.yakuraion.confession.commonui.compose.theme.AppTheme
import pro.yakuraion.confession.commonui.openLink
import pro.yakuraion.confession.domain.models.Topic

@DestinationScreen
@Composable
fun TopicsScreen(
    viewModel: TopicsViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    TopicsScreen(
        topics = viewModel.topics.toImmutableList(),
        onTopicClick = { topic ->
            context.openLink(Uri.parse(topic.url))
        }
    )
}

@Composable
private fun TopicsScreen(
    topics: ImmutableList<Topic>,
    onTopicClick: (Topic) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawBackground()
    ) {
        CurveTopBar(
            title = stringResource(id = R.string.topics_title),
            bigImageResId = R.drawable.il_topics_big,
        )

        Spacer(modifier = Modifier.height(40.dp))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 140.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            itemsIndexed(topics) { index, topic ->
                val drawableRes = when (index) {
                    0 -> R.drawable.preview_1
                    1 -> R.drawable.preview_2
                    2 -> R.drawable.preview_3
                    3 -> R.drawable.preview_4
                    4 -> R.drawable.preview_5
                    else -> R.drawable.preview_1
                }

                TopicBlock(
                    topic = topic,
                    drawableRes = drawableRes,
                    onClick = { onTopicClick.invoke(topic) },
                )
            }
        }
    }
}

@Composable
private fun TopicBlock(
    topic: Topic,
    drawableRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val shape = RoundedCornerShape(20.dp)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Image(
            painter = painterResource(id = drawableRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(100.dp)
                .clip(shape)
                .clickable { onClick.invoke() },
        )
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
    val topics = persistentListOf(
        Topic("title"),
        Topic(""),
        Topic(""),
        Topic(""),
        Topic(""),
    )
    AppTheme {
        TopicsScreen(
            topics = topics,
            onTopicClick = {}
        )
    }
}
