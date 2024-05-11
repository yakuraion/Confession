package pro.yakuraion.confession.commonui.compose.widgets.onboarding

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

sealed class OnboardingLayerElement {

    abstract fun getOffset(
        parentSize: Size,
        leftInset: Float,
        rightInset: Float,
        topInset: Float,
        bottomInset: Float,
    ): Offset

    class Rect(
        val width: Dimension,
        val height: Dimension,
        val leftMargin: Float?,
        val topMargin: Float?,
        val rightMargin: Float?,
        val bottomMargin: Float?,
        val cornerRadius: CornerRadius,
    ) : OnboardingLayerElement() {

        fun getSize(
            parentSize: Size,
            leftInset: Float,
            rightInset: Float,
            topInset: Float,
            bottomInset: Float,
        ): Size {
            val width = when (width) {
                is Dimension.Fixed -> width.value
                is Dimension.FillToParent -> parentSize.width - leftInset - rightInset - (leftMargin ?: 0f) - (rightMargin ?: 0f)
            }
            val height = when (height) {
                is Dimension.Fixed -> height.value
                is Dimension.FillToParent -> parentSize.height - topInset - bottomInset - (topMargin ?: 0f) - (bottomMargin ?: 0f)
            }
            return Size(width, height)
        }

        override fun getOffset(parentSize: Size, leftInset: Float, rightInset: Float, topInset: Float, bottomInset: Float): Offset {
            val size = getSize(parentSize, leftInset, rightInset, topInset, bottomInset)
            val x = when {
                leftMargin != null && rightMargin != null -> {
                    val spaceWidth = parentSize.width - leftInset - rightInset - leftMargin - rightMargin
                    val center = leftInset + leftMargin + spaceWidth / 2
                    center - size.width / 2
                }

                leftMargin != null -> {
                    leftInset + leftMargin
                }

                rightMargin != null -> {
                    parentSize.width - rightInset - rightMargin - size.width
                }

                else -> {
                    0f
                }
            }

            val y = when {
                topMargin != null && bottomMargin != null -> {
                    val spaceHeight = parentSize.height - topInset - bottomInset - topMargin - bottomMargin
                    val center = topInset + topMargin + spaceHeight / 2
                    center - size.height / 2
                }

                topMargin != null -> {
                    topInset + topMargin
                }

                bottomMargin != null -> {
                    parentSize.height - bottomInset - bottomMargin - size.height
                }

                else -> {
                    0f
                }
            }
            return Offset(x, y)
        }
    }

    class Circle(
        val radius: Float,
        val leftMargin: Float?,
        val topMargin: Float?,
        val rightMargin: Float?,
        val bottomMargin: Float?,
    ) : OnboardingLayerElement() {

        override fun getOffset(parentSize: Size, leftInset: Float, rightInset: Float, topInset: Float, bottomInset: Float): Offset {
            val x = when {
                leftMargin != null && rightMargin != null -> {
                    val spaceWidth = parentSize.width - leftInset - leftMargin - rightInset - rightMargin
                    leftInset + leftMargin + spaceWidth / 2
                }

                leftMargin != null -> {
                    leftInset + leftMargin + radius
                }

                rightMargin != null -> {
                    parentSize.width - rightInset - rightMargin - radius
                }

                else -> {
                    radius
                }
            }

            val y = when {
                topMargin != null && bottomMargin != null -> {
                    val spaceHeight = parentSize.height - topInset - topMargin - bottomInset - bottomMargin
                    topInset + topMargin + spaceHeight / 2
                }

                topMargin != null -> {
                    topInset + topMargin + radius
                }

                bottomMargin != null -> {
                    parentSize.height - bottomInset - bottomMargin - radius
                }

                else -> {
                    radius
                }
            }

            return Offset(x, y)
        }
    }

    sealed class Dimension {
        data object FillToParent : Dimension()
        class Fixed(val value: Float) : Dimension()
    }
}
