package pro.yakuraion.confession.commonui.compose.theme.extended

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun ExtendedMaterialTheme(
    colorScheme: ExtendedColorScheme,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalExtendedColorScheme provides colorScheme
    ) {
        content()
    }
}

object ExtendedMaterialTheme {

    val colorScheme: ExtendedColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalExtendedColorScheme.current
}
