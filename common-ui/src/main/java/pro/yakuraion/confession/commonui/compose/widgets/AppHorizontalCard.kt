package pro.yakuraion.confession.commonui.compose.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pro.yakuraion.confession.commonui.compose.theme.AppTheme
import pro.yakuraion.confession.commonui.compose.theme.extended.ExtendedMaterialTheme

enum class AppHorizontalCardStyle {
    PRIMARY, SECONDARY, TERTIARY, SURFACE
}

data class AppHorizontalCardAddOptions(
    val title: String? = null,
    val icon: (@Composable () -> Unit)? = null,
    val onClick: () -> Unit,
)

@Composable
fun AppHorizontalCard(
    mainTitle: String,
    modifier: Modifier = Modifier,
    style: AppHorizontalCardStyle = AppHorizontalCardStyle.SURFACE,
    mainIcon: (@Composable () -> Unit)? = null,
    addOptions: AppHorizontalCardAddOptions? = null,
) {
    val shape = RoundedCornerShape(12.dp)

    Row(
        modifier = modifier
            .height(64.dp)
            .background(style.getBackgroundColor(), shape)
            .border(1.dp, MaterialTheme.colorScheme.outline, shape)
            .clip(shape)
            .clickable(enabled = addOptions != null, onClick = { addOptions!!.onClick() }),
    ) {
        CompositionLocalProvider(
            LocalContentColor provides style.getContentColor()
        ) {
            MainSection(
                title = mainTitle,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                icon = mainIcon
            )
            if (addOptions != null) {
                AddSection(
                    modifier = Modifier
                        .fillMaxHeight(),
                    title = addOptions.title,
                    icon = addOptions.icon,
                )
            }
        }
    }
}

@Composable
private fun MainSection(
    title: String,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)?,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let { icon ->
            icon.invoke()
            Spacer(modifier = Modifier.width(16.dp))
        }
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
private fun AddSection(
    modifier: Modifier = Modifier,
    title: String?,
    icon: (@Composable () -> Unit)?,
) {
    val horizontalPadding = if (title != null) 16.dp else 20.dp
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = horizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        title?.let { Text(text = it, style = MaterialTheme.typography.bodyLarge) }
        icon?.invoke()
    }
}

@Composable
private fun AppHorizontalCardStyle.getBackgroundColor() = when (this) {
    AppHorizontalCardStyle.PRIMARY -> MaterialTheme.colorScheme.primaryContainer
    AppHorizontalCardStyle.SECONDARY -> MaterialTheme.colorScheme.secondaryContainer
    AppHorizontalCardStyle.TERTIARY -> MaterialTheme.colorScheme.tertiaryContainer
    AppHorizontalCardStyle.SURFACE -> ExtendedMaterialTheme.colorScheme.surfaceContainer
}

@Composable
private fun AppHorizontalCardStyle.getContentColor() = when (this) {
    AppHorizontalCardStyle.PRIMARY -> MaterialTheme.colorScheme.onPrimaryContainer
    AppHorizontalCardStyle.SECONDARY -> MaterialTheme.colorScheme.onSecondaryContainer
    AppHorizontalCardStyle.TERTIARY -> MaterialTheme.colorScheme.onTertiaryContainer
    AppHorizontalCardStyle.SURFACE -> MaterialTheme.colorScheme.onSurface
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppHorizontalCardPreview() {
    AppTheme {
        AppHorizontalCard(
            mainTitle = "Main Title",
            modifier = Modifier.fillMaxWidth(),
            style = AppHorizontalCardStyle.PRIMARY,
            mainIcon = { Icon(imageVector = Icons.Filled.Done, contentDescription = null) },
            addOptions = AppHorizontalCardAddOptions(
                onClick = {},
                title = "Add title",
                icon = { Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null) },
            ),
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppHorizontalCardPreviewWithoutMainIcon() {
    AppTheme {
        AppHorizontalCard(
            mainTitle = "Main Title",
            modifier = Modifier.fillMaxWidth(),
            style = AppHorizontalCardStyle.PRIMARY,
            mainIcon = null,
            addOptions = AppHorizontalCardAddOptions(
                onClick = {},
                title = "Add title",
                icon = { Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null) },
            ),
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppHorizontalCardPreviewWithoutAddTitle() {
    AppTheme {
        AppHorizontalCard(
            mainTitle = "Main Title",
            modifier = Modifier.fillMaxWidth(),
            style = AppHorizontalCardStyle.PRIMARY,
            mainIcon = { Icon(imageVector = Icons.Filled.Done, contentDescription = null) },
            addOptions = AppHorizontalCardAddOptions(
                onClick = {},
                icon = { Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null) },
            ),
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppHorizontalCardPreviewWithoutAddIcon() {
    AppTheme {
        AppHorizontalCard(
            mainTitle = "Main Title",
            modifier = Modifier.fillMaxWidth(),
            style = AppHorizontalCardStyle.PRIMARY,
            mainIcon = { Icon(imageVector = Icons.Filled.Done, contentDescription = null) },
            addOptions = AppHorizontalCardAddOptions(
                onClick = {},
                title = "Add title",
            ),
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppHorizontalCardPreviewWithoutAdd() {
    AppTheme {
        AppHorizontalCard(
            mainTitle = "Main Title",
            modifier = Modifier.fillMaxWidth(),
            style = AppHorizontalCardStyle.PRIMARY,
            mainIcon = { Icon(imageVector = Icons.Filled.Done, contentDescription = null) },
        )
    }
}
