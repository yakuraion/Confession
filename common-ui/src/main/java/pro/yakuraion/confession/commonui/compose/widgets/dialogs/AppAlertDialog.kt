package pro.yakuraion.confession.commonui.compose.widgets.dialogs

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import pro.yakuraion.confession.commonui.compose.theme.AppTheme
import pro.yakuraion.confession.commonui.compose.theme.extended.ExtendedMaterialTheme

@Composable
fun AppAlertDialog(
    onDismissRequest: () -> Unit,
    title: String,
    description: CharSequence,
    primaryButtonText: String,
    onPrimaryButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    secondaryButtonText: String? = null,
    onSecondaryButtonClick: (() -> Unit)? = null,
    properties: DialogProperties = DialogProperties(),
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        properties = properties,
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = ExtendedMaterialTheme.colorScheme.surfaceContainer,
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(24.dp)
        ) {
            Title(text = title)

            Spacer(modifier = Modifier.height(16.dp))

            Description(
                text = description,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            ButtonsRow(
                primaryButtonText = primaryButtonText,
                onPrimaryButtonClick = onPrimaryButtonClick,
                secondaryButtonText = secondaryButtonText,
                onSecondaryButtonClick = onSecondaryButtonClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun Title(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.headlineSmall,
    )
}

@Composable
private fun Description(
    text: CharSequence,
    modifier: Modifier = Modifier,
) {
    when (text) {
        is String -> {
            Text(
                text = text,
                modifier = modifier,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        is AnnotatedString -> {
            Text(
                text = text,
                modifier = modifier,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun ButtonsRow(
    primaryButtonText: String,
    onPrimaryButtonClick: () -> Unit,
    secondaryButtonText: String?,
    onSecondaryButtonClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        if (secondaryButtonText != null && onSecondaryButtonClick != null) {
            Button(onClick = onSecondaryButtonClick, text = secondaryButtonText)
            Spacer(modifier = Modifier.width(8.dp))
        }
        Button(onClick = onPrimaryButtonClick, text = primaryButtonText)
    }
}

@Composable
private fun Button(
    onClick: () -> Unit,
    text: String,
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AppAlertDialogPreview() {
    AppTheme {
        AppAlertDialog(
            onDismissRequest = { },
            title = "Title",
            description = "Long description, ".repeat(6),
            primaryButtonText = "Primary",
            onPrimaryButtonClick = {},
        )
    }
}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AppAlertDialogPreviewBothButtons() {
    AppTheme {
        AppAlertDialog(
            onDismissRequest = { },
            title = "Title",
            description = "Long description, ".repeat(6),
            primaryButtonText = "Primary",
            onPrimaryButtonClick = {},
            secondaryButtonText = "Secondary",
            onSecondaryButtonClick = {},
        )
    }
}
