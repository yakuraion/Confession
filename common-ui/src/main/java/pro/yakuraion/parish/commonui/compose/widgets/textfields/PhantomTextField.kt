package pro.yakuraion.parish.commonui.compose.widgets.textfields

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PhantomTextField(
    modifier: Modifier = Modifier,
) {
    BasicTextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .height(0.dp),
    )
}
