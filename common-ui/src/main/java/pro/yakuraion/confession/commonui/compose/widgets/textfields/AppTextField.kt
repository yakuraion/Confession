package pro.yakuraion.confession.commonui.compose.widgets.textfields

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pro.yakuraion.androidcommon.kotlin.applyIf
import pro.yakuraion.confession.commonui.compose.theme.AppTheme

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    placeholderText: String = "",
    error: AppTextFieldError? = null,
    label: AppTextFieldLabel? = null,
    actionIcon: AppTextFieldActionIcon? = null,
    shapes: AppTextFieldShapes = AppTextFieldDefaults.shapes(),
    shadowElevation: Dp = 0.dp,
    focusRequest: FocusRequester? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    Column(modifier = modifier) {
        TopLabelsRow(
            error = error,
            label = label,
        )
        Surface(
            shape = shapes.shape,
            shadowElevation = shadowElevation,
        ) {
            ErrorBorderBox(
                isError = error?.isError == true,
                shape = shapes.shape,
                modifier = Modifier.height(IntrinsicSize.Min)
            ) {
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .applyIf(focusRequest != null) {
                            focusRequester(focusRequest!!)
                        },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    placeholder = {
                        Text(
                            text = placeholderText,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    trailingIcon = {},
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    maxLines = maxLines,
                    shape = shapes.shape,
                    colors = transparentTextFieldColors()
                )
                if (actionIcon != null) {
                    IconLoadingActionContainer(
                        icon = actionIcon.icon,
                        onClick = actionIcon.onClick,
                        isEnabled = actionIcon.isEnabled,
                        isLoading = actionIcon.isLoading,
                        shape = shapes.actionShape,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }
        }
        BottomLabelsRow(
            error = error,
            label = label,
        )
    }
}

@Composable
private fun TopLabelsRow(
    error: AppTextFieldError?,
    label: AppTextFieldLabel?,
    modifier: Modifier = Modifier,
) {
    val isErrorFits = error?.position?.isTop == true
    val isLabelFits = label?.position?.isTop == true
    if (isErrorFits || isLabelFits) {
        LabelsRow(
            error = if (isErrorFits) error else null,
            label = if (isLabelFits) label else null,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(2.dp))
    }
}

@Composable
private fun BottomLabelsRow(
    error: AppTextFieldError?,
    label: AppTextFieldLabel?,
    modifier: Modifier = Modifier,
) {
    val isErrorFits = error?.position?.isTop == false
    val isLabelFits = label?.position?.isTop == false
    if (isErrorFits || isLabelFits) {
        LabelsRow(
            error = if (isErrorFits) error else null,
            label = if (isLabelFits) label else null,
            modifier = modifier
        )
        Spacer(modifier = Modifier.height(2.dp))
    }
}

@Composable
private fun LabelsRow(
    error: AppTextFieldError?,
    label: AppTextFieldLabel?,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        error?.let {
            ErrorLabel(
                text = error.text,
                modifier = Modifier.align(error.position.alignmentInBox)
            )
        }
        label?.let {
            Label(
                text = label.text,
                modifier = Modifier.align(label.position.alignmentInBox)
            )
        }
    }
}

@Composable
private fun transparentTextFieldColors() = TextFieldDefaults.textFieldColors(
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    errorIndicatorColor = Color.Transparent
)

@Composable
private fun ErrorBorderBox(
    isError: Boolean,
    shape: Shape,
    modifier: Modifier = Modifier,
    errorBorderStroke: BorderStroke = BorderStroke(
        width = 2.dp,
        color = MaterialTheme.colorScheme.error
    ),
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .applyIf(isError) {
                border(
                    border = errorBorderStroke,
                    shape = shape
                )
            },
        content = content
    )
}

@Composable
private fun ErrorLabel(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.labelMedium
    )
}

@Composable
private fun Label(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.labelMedium
    )
}

@Composable
private fun IconLoadingActionContainer(
    icon: ImageVector,
    onClick: () -> Unit,
    isEnabled: Boolean,
    isLoading: Boolean,
    shape: Shape,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
) {
    val containerWidth = 56.dp
    val progressSize = 24.dp
    val iconSize = 24.dp

    ActionContainer(
        onClick = onClick,
        onClickEnabled = !isLoading && isEnabled,
        shape = shape,
        enabled = isEnabled,
        containerColor = containerColor,
        contentColor = contentColor,
        modifier = modifier
            .width(containerWidth)
            .clickable(
                enabled = !isLoading && isEnabled,
                onClick = onClick
            )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(progressSize)
                    .align(Alignment.Center),
                color = LocalContentColor.current
            )
        } else {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(iconSize)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun ActionContainer(
    onClick: () -> Unit,
    onClickEnabled: Boolean,
    shape: Shape,
    enabled: Boolean,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val disabledAlpha = 0.4f

    Box(
        modifier = modifier
            .alpha(if (enabled) 1f else disabledAlpha)
            .fillMaxSize()
            .clip(shape)
            .background(containerColor)
            .clickable(enabled = onClickEnabled, onClick = onClick)
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            content()
        }
    }
}

data class AppTextFieldActionIcon(
    val icon: ImageVector,
    val onClick: () -> Unit,
    val isEnabled: Boolean = true,
    val isLoading: Boolean = false,
)

data class AppTextFieldError(
    val isError: Boolean,
    val text: String,
    val position: AppTextFieldLabelPosition = AppTextFieldLabelPosition.START_BOTTOM,
)

data class AppTextFieldLabel(
    val text: String,
    val position: AppTextFieldLabelPosition = AppTextFieldLabelPosition.START_TOP,
)

enum class AppTextFieldLabelPosition(
    val isTop: Boolean,
    val alignmentInBox: Alignment,
) {

    START_TOP(true, Alignment.CenterStart),
    END_TOP(true, Alignment.CenterEnd),
    END_BOTTOM(false, Alignment.CenterEnd),
    START_BOTTOM(false, Alignment.CenterStart),
}

data class AppTextFieldShapes(
    val shape: Shape,
    val actionShape: Shape,
)

object AppTextFieldDefaults {

    private val CornerRadius = 8.dp

    fun shapes(
        shape: Shape = RoundedCornerShape(CornerRadius),
        actionShape: Shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = CornerRadius,
            bottomEnd = CornerRadius,
            bottomStart = 0.dp
        ),
    ) = AppTextFieldShapes(
        shape = shape,
        actionShape = actionShape
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppTextFieldPreview() {
    AppTheme {
        val context = LocalContext.current
        var value by remember { mutableStateOf("") }
        AppTextField(
            value = value,
            onValueChange = { value = it },
            modifier = Modifier.padding(16.dp),
            placeholderText = "Placeholder",
            actionIcon = AppTextFieldActionIcon(
                icon = Icons.Default.Done,
                onClick = {
                    Toast.makeText(context, "On action click", Toast.LENGTH_SHORT).show()
                }
            ),
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppTextFieldWithTextPreview() {
    AppTheme {
        AppTextField(
            value = "Some text",
            onValueChange = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppTextFieldWithErrorPreview() {
    AppTheme {
        AppTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.padding(16.dp),
            error = AppTextFieldError(
                isError = true,
                text = "Error text"
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppTextFieldWithEmptyErrorPreview() {
    AppTheme {
        AppTextField(
            value = "",
            onValueChange = { },
            modifier = Modifier.padding(16.dp),
            error = AppTextFieldError(
                isError = false,
                text = ""
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppTextFieldLoadingIconPreview() {
    AppTheme {
        AppTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.padding(16.dp),
            actionIcon = AppTextFieldActionIcon(
                icon = Icons.Default.Done,
                onClick = {},
                isLoading = true
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppTextFieldDisabledIconPreview() {
    AppTheme {
        AppTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.padding(16.dp),
            actionIcon = AppTextFieldActionIcon(
                icon = Icons.Default.Done,
                onClick = {},
                isEnabled = false
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppTextFieldIconWithErrorPreview() {
    AppTheme {
        AppTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.padding(16.dp),
            actionIcon = AppTextFieldActionIcon(
                icon = Icons.Default.Done,
                onClick = {},
                isEnabled = true
            ),
            error = AppTextFieldError(
                isError = true,
                text = "Error text"
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppTextFieldDisabledIconWithErrorPreview() {
    AppTheme {
        AppTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.padding(16.dp),
            actionIcon = AppTextFieldActionIcon(
                icon = Icons.Default.Done,
                onClick = {},
                isEnabled = false
            ),
            error = AppTextFieldError(
                isError = true,
                text = "Error text"
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AppTextFieldPreviewWithTopErrorAndBottomLabel() {
    AppTheme {
        AppTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.padding(16.dp),
            error = AppTextFieldError(
                isError = true,
                text = "Error text",
                position = AppTextFieldLabelPosition.START_TOP,
            ),
            label = AppTextFieldLabel(
                text = "Label text",
                position = AppTextFieldLabelPosition.END_BOTTOM,
            )
        )
    }
}
