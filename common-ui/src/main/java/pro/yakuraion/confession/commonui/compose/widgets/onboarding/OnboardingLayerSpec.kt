package pro.yakuraion.confession.commonui.compose.widgets.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class OnboardingLayerSpec {

    private val _elements = mutableListOf<OnboardingLayerElement>()
    val elements: List<OnboardingLayerElement> = _elements

    fun clear() {
        _elements.clear()
    }

    @Composable
    fun Rect(
        width: Dimension,
        height: Dimension,
        leftMargin: Dp? = null,
        topMargin: Dp? = null,
        rightMargin: Dp? = null,
        bottomMargin: Dp? = null,
        cornerSize: Dp = 0.dp,
    ) {
        _elements.add(
            with(LocalDensity.current) {
                OnboardingLayerElement.Rect(
                    width = width.toElementDimension(this),
                    height = height.toElementDimension(this),
                    leftMargin = leftMargin?.toPx(),
                    topMargin = topMargin?.toPx(),
                    rightMargin = rightMargin?.toPx(),
                    bottomMargin = bottomMargin?.toPx(),
                    cornerRadius = CornerRadius(cornerSize.toPx(), cornerSize.toPx()),
                )
            }
        )
    }

    @Composable
    fun Circle(
        radius: Dp,
        leftMargin: Dp? = null,
        topMargin: Dp? = null,
        rightMargin: Dp? = null,
        bottomMargin: Dp? = null,
    ) {
        _elements.add(
            with(LocalDensity.current) {
                OnboardingLayerElement.Circle(
                    radius = radius.toPx(),
                    leftMargin = leftMargin?.toPx(),
                    topMargin = topMargin?.toPx(),
                    rightMargin = rightMargin?.toPx(),
                    bottomMargin = bottomMargin?.toPx(),
                )
            }
        )
    }

    sealed class Dimension {
        data object FillToParent : Dimension()
        class Fixed(val value: Dp) : Dimension()

        fun toElementDimension(density: Density): OnboardingLayerElement.Dimension {
            return when (this) {
                is FillToParent -> OnboardingLayerElement.Dimension.FillToParent
                is Fixed -> OnboardingLayerElement.Dimension.Fixed(with(density) { value.toPx() })
            }
        }
    }
}
