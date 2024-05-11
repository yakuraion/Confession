package pro.yakuraion.confession.commonui.compose.widgets.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayoutScope

@Composable
fun Onboarding(
    layersCount: Int,
    initSpec: @Composable OnboardingLayerSpec.(layerNumber: Int) -> Unit,
    content: @Composable ConstraintLayoutScope.(layerNumber: Int) -> Unit,
    onOnboardingFinished: () -> Unit,
    firstLayerIndex: Int = 0,
    windowInsets: WindowInsets = WindowInsets.systemBars,
) {
    var currentLayerIndex by rememberSaveable { mutableIntStateOf(firstLayerIndex) }

    val interactionSource = remember { MutableInteractionSource() }

    if (currentLayerIndex < layersCount) {
        OnboardingLayer(
            windowInsets = windowInsets,
            initSpec = { initSpec(currentLayerIndex) },
            content = { content(currentLayerIndex) },
            modifier = Modifier
                .clickable(interactionSource = interactionSource, indication = null) {
                    currentLayerIndex++
                },
        )
    }

    LaunchedEffect(currentLayerIndex) {
        if (currentLayerIndex >= layersCount) {
            onOnboardingFinished.invoke()
        }
    }
}
