package pro.yakuraion.confession.commonui.compose.widgets.buttons

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AppCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.White,
            disabledCheckedColor = Color.White,
            uncheckedColor = Color.White,
            disabledUncheckedColor = Color.White,
            checkmarkColor = Color.Black,
            disabledIndeterminateColor = Color.Black,
        ),
        interactionSource = interactionSource,
    )
}
