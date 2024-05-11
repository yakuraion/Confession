package pro.yakuraion.confession.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import pro.yakuraion.confession.commonui.R

@Composable
fun CurveTopBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    subtitle: String? = null,
    @DrawableRes bigImageResId: Int? = null,
) {
    val shape = RoundedCornerShape(bottomStart = 60.dp)
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(shape)
            .drawBackground()
            .padding(top = 20.dp),
    ) {
        val (backButtonRef, titleRef, subtitleRef, bigIconRef) = createRefs()

        onBackClick?.let {
            NavigationIconBack(
                onClick = onBackClick,
                modifier = Modifier.constrainAs(backButtonRef) {
                    start.linkTo(parent.start, 20.dp)
                    top.linkTo(parent.top, 20.dp)
                }
            )
        }

        bigImageResId?.let {
            Image(
                painter = painterResource(bigImageResId),
                contentDescription = null,
                modifier = Modifier
                    .height(140.dp)
                    .constrainAs(bigIconRef) {
                        bottom.linkTo(parent.bottom, (-20).dp)
                        end.linkTo(parent.end, 20.dp)
                    },
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = title,
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(parent.top, 80.dp)
                start.linkTo(parent.start, 50.dp)
            },
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
        )

        subtitle?.let {
            Text(
                text = subtitle,
                modifier = Modifier.constrainAs(subtitleRef) {
                    top.linkTo(titleRef.bottom)
                    start.linkTo(titleRef.start)
                },
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }
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
        0.22f to Color(0xFFB63DFF),
        0.65f to Color(0xFF6A02DD),
        1f to Color(0xFF5D03CF),
        start = start,
        end = end,
    )
}

@Preview
@Composable
private fun Preview() {
    CurveTopBar(
        title = "Title",
        subtitle = "Subtitle",
        bigImageResId = R.drawable.il_confession_big,
        onBackClick = {},
    )
}
