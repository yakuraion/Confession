package pro.yakuraion.confession.sins.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pro.yakuraion.confession.commonui.R
import pro.yakuraion.confession.commonui.compose.theme.AppTheme
import pro.yakuraion.confession.domain.models.SinTopic
import pro.yakuraion.confession.sins.SinsState

@Composable
fun SinsList(
    state: SinsState.Content,
    modifier: Modifier = Modifier,
) {
    val expandedTopics = remember { mutableStateMapOf<SinTopic, Boolean>() }

    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        modifier = modifier,
    ) {
        state.topics.forEachIndexed { index, topic ->
            val expanded = expandedTopics[topic] ?: false

            item(key = topic.title.hashCode()) {
                Column {
                    Spacer(modifier = Modifier.height(14.dp))

                    TopicBlock(
                        sinTopic = topic,
                        expanded = expanded,
                        onExpandChange = { expanded ->
                            expandedTopics[topic] = expanded
                        },
                        modifier = Modifier,
                    )
                }

            }

            items(topic.questions) { question ->
                AnimatedVisibility(
                    visible = expanded,
                    enter = fadeIn(tween(200)) + expandVertically(tween(200)),
                    exit = fadeOut(tween(200)) + shrinkVertically(tween(200)),
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(14.dp))

                        QuestionBlock(
                            question = question,
                            modifier = Modifier,
                        )
                    }
                }
            }
        }

//            AnimatedVisibility(
//                visible = isExpanded,
//                enter = fadeIn(tween(EXPAND_ANIMATION_MILLIS)) + expandVertically(tween(EXPAND_ANIMATION_MILLIS)),
//            ) {
//                SingleDefinition(
//                    definition = definition,
//                    isFirst = false,
//                )
//            }
//
//            if (expanded) {
//                items(topic.questions.count(), key = { topic.questions[it].text.hashCode() }) { index ->
//                    QuestionBlock(
//                        question = topic.questions[index],
//                        modifier = Modifier
//                            .animateItemPlacement(),
//                    )
//                }
//            }
    }
}

@Composable
private fun TopicBlock(
    sinTopic: SinTopic,
    expanded: Boolean,
    onExpandChange: (expanded: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(20.dp)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0x57200145), shape)
            .clip(shape)
            .clickable { onExpandChange.invoke(!expanded) }
            .padding(start = 30.dp, end = 20.dp, top = 20.dp, bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = sinTopic.title,
            modifier = Modifier.weight(1f),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.width(8.dp))

        val rotateAngle = if (expanded) 180f else 0f
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .rotate(rotateAngle),
        )
    }
}

@Composable
private fun QuestionBlock(
    question: SinTopic.Question,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0x33FFFFFF), RoundedCornerShape(20.dp))
            .padding(horizontal = 30.dp, vertical = 20.dp)
    ) {
        Text(
            text = question.text,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview
@Composable
private fun PreviewTopic() {
    AppTheme {
        TopicBlock(
            sinTopic = SinTopic(
                title = "some title",
                questions = listOf(SinTopic.Question("Question 1"), SinTopic.Question("Question 2")),
            ),
            expanded = false,
            onExpandChange = {},
        )
    }
}

@Preview
@Composable
private fun PreviewQuestion() {
    AppTheme {
        QuestionBlock(
            question = SinTopic.Question("Some question")
        )
    }
}
