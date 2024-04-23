package pro.yakuraion.parish.commonui.compose.widgets.buttons

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pro.yakuraion.parish.commonui.compose.theme.AppTheme

@Composable
fun AppButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    type: AppButtonType = AppButtonType.FILLED,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = isEnabled && !isLoading,
        shape = RoundedCornerShape(8.dp),
        colors = type.getButtonColors(isEnabled = isEnabled, isLoading = isLoading),
        border = type.getBorder(isEnabled),
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = LocalContentColor.current,
                strokeWidth = 3.dp
            )
        } else {
            Text(text = text)
        }
    }
}

enum class AppButtonType {

    FILLED {

        @Composable
        override fun getButtonColors(isEnabled: Boolean, isLoading: Boolean): ButtonColors {
            val keepColorsForDisabled = isLoading && isEnabled
            val containerColor = MaterialTheme.colorScheme.primaryContainer
            val contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            return ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor,
                disabledContainerColor = if (keepColorsForDisabled) containerColor else containerColor.copy(alpha = 0.5f),
                disabledContentColor = if (keepColorsForDisabled) contentColor else contentColor.copy(alpha = 0.5f),
            )
        }

        @Composable
        override fun getBorder(isEnabled: Boolean): BorderStroke? = null
    },

    OUTLINED {

        @Composable
        override fun getButtonColors(isEnabled: Boolean, isLoading: Boolean): ButtonColors {
            val keepColorsForDisabled = isLoading && isEnabled
            val contentColor = MaterialTheme.colorScheme.primary
            return ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = contentColor,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = if (keepColorsForDisabled) contentColor else contentColor.copy(alpha = 0.5f),
            )
        }

        @Composable
        override fun getBorder(isEnabled: Boolean): BorderStroke = BorderStroke(
            width = 1.dp,
            color = if (isEnabled) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            }
        )
    };

    @Composable
    abstract fun getButtonColors(isEnabled: Boolean, isLoading: Boolean): ButtonColors

    @Composable
    abstract fun getBorder(isEnabled: Boolean): BorderStroke?
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, backgroundColor = 0xff000000)
@Composable
private fun AppButtonPreview() {
    AppTheme {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            AppButton(
                onClick = {},
                text = "Primary",
                modifier = Modifier.fillMaxWidth(),
            )

            AppButton(
                onClick = {},
                text = "Primary",
                modifier = Modifier.fillMaxWidth(),
                isLoading = true,
            )

            AppButton(
                onClick = {},
                text = "Primary",
                modifier = Modifier.fillMaxWidth(),
                isEnabled = false,
            )

            AppButton(
                onClick = {},
                text = "Primary",
                modifier = Modifier.fillMaxWidth(),
                isEnabled = false,
                isLoading = true,
            )

            AppButton(
                onClick = {},
                text = "Primary",
                modifier = Modifier.fillMaxWidth(),
                type = AppButtonType.OUTLINED,
            )

            AppButton(
                onClick = {},
                text = "Primary",
                modifier = Modifier.fillMaxWidth(),
                type = AppButtonType.OUTLINED,
                isLoading = true,
            )

            AppButton(
                onClick = {},
                text = "Primary",
                modifier = Modifier.fillMaxWidth(),
                type = AppButtonType.OUTLINED,
                isEnabled = false,
            )

            AppButton(
                onClick = {},
                text = "Primary",
                modifier = Modifier.fillMaxWidth(),
                type = AppButtonType.OUTLINED,
                isEnabled = false,
                isLoading = true,
            )
        }
    }
}
