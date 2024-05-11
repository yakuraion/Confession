package pro.yakuraion.confession.commonui.compose.theme.extended

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
class ExtendedColorScheme(
    surfaceContainerHighest: Color,
    surfaceContainerHigh: Color,
    surfaceContainer: Color,
    surfaceContainerLow: Color,
    surfaceContainerLowest: Color,
) {

    var surfaceContainerHighest by mutableStateOf(surfaceContainerHighest)
        internal set

    var surfaceContainerHigh by mutableStateOf(surfaceContainerHigh)
        internal set

    var surfaceContainer by mutableStateOf(surfaceContainer)
        internal set

    var surfaceContainerLow by mutableStateOf(surfaceContainerLow)
        internal set

    var surfaceContainerLowest by mutableStateOf(surfaceContainerLowest)
        internal set
}

internal val LocalExtendedColorScheme = staticCompositionLocalOf {
    ExtendedColorScheme(Color.Black, Color.Black, Color.Black, Color.Black, Color.Black)
}
