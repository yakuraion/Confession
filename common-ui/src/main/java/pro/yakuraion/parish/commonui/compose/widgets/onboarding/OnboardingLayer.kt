package pro.yakuraion.parish.commonui.compose.widgets.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import pro.yakuraion.parish.commonui.compose.theme.AppTheme

@Composable
fun OnboardingLayer(
    windowInsets: WindowInsets,
    initSpec: @Composable OnboardingLayerSpec.() -> Unit,
    content: @Composable ConstraintLayoutScope.() -> Unit,
    modifier: Modifier = Modifier,
) {
    val layoutSpec = remember(initSpec) { OnboardingLayerSpec() }
    layoutSpec.clear()
    initSpec.invoke(layoutSpec)

    val density = LocalDensity.current
    val leftInset = windowInsets.getLeft(density, LayoutDirection.Ltr).toFloat() // todo adapt Rtr
    val rightInset = windowInsets.getRight(density, LayoutDirection.Ltr).toFloat() // todo adapt Rtr
    val topInset = windowInsets.getTop(density).toFloat()
    val bottomInset = windowInsets.getBottom(density).toFloat()

    Box(
        modifier = modifier
            .graphicsLayer(alpha = 0.99f)
            .drawWithContent {
                drawContent()

                for (element in layoutSpec.elements) {
                    when (element) {
                        is OnboardingLayerElement.Rect -> {
                            drawRoundRect(
                                color = Color.Black,
                                topLeft = element.getOffset(size, leftInset, rightInset, topInset, bottomInset),
                                size = element.getSize(size, leftInset, rightInset, topInset, bottomInset),
                                cornerRadius = element.cornerRadius,
                                blendMode = BlendMode.DstOut,
                            )
                        }

                        is OnboardingLayerElement.Circle -> {
                            drawCircle(
                                color = Color.Black,
                                radius = element.radius,
                                center = element.getOffset(size, leftInset, rightInset, topInset, bottomInset),
                                blendMode = BlendMode.DstOut,
                            )
                        }
                    }
                }
            },
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f))
        )
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(windowInsets)
                .consumeWindowInsets(windowInsets)
        ) {
            content()
        }
    }
}

@Preview(apiLevel = 33)
@Composable
private fun Preview() {
    AppTheme {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        )
        OnboardingLayer(
            windowInsets = WindowInsets.systemBars,
            initSpec = {
                Rect(
                    width = OnboardingLayerSpec.Dimension.Fixed(200.dp),
                    height = OnboardingLayerSpec.Dimension.Fixed(300.dp),
                    leftMargin = 0.dp,
                    rightMargin = 0.dp,
                    topMargin = 300.dp,
                    cornerSize = 8.dp,
                )
                Circle(
                    radius = 20.dp,
                    leftMargin = 20.dp,
                    topMargin = 20.dp,
                )
            },
            content = {
                val (titleText, subtitleText) = createRefs()

                OnboardingText(
                    text = "Title",
                    modifier = Modifier
                        .constrainAs(titleText) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top, 32.dp)
                        }
                )

                OnboardingText(
                    text = "Subtitle",
                    modifier = Modifier
                        .constrainAs(subtitleText) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(titleText.bottom, 16.dp)
                        }
                )
            }
        )
    }
}
