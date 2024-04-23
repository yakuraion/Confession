package pro.yakuraion.parish.commonui.compose.widgets.menu

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pro.yakuraion.androidcommon.kotlin.applyIf
import pro.yakuraion.parish.commonui.compose.theme.AppTheme

@Composable
fun AppDropdownMenuAnchor(
    text: String,
    isExpanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    outlined: Boolean = true,
) {
    val trailingIcon = if (isExpanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown

    val shape = RoundedCornerShape(8.dp)
    Row(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .applyIf(outlined) {
                border(
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    shape = shape
                )
            }
            .clip(shape)
            .clickable { onClick.invoke() }
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(6.dp))
        } else {
            Spacer(modifier = Modifier.width(10.dp))
        }

        Text(
            text = text,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.width(6.dp))

        Icon(
            imageVector = trailingIcon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
fun AppDropdownMenuItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DropdownMenuItem(
        text = {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelLarge
            )
        },
        onClick = onClick,
        modifier = modifier,
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AppMenuLabelPreview() {
    AppTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            AppDropdownMenuAnchor(
                text = "Label",
                isExpanded = false,
                onClick = {},
            )
            AppDropdownMenuAnchor(
                text = "Label",
                isExpanded = false,
                onClick = {},
                leadingIcon = Icons.Filled.FilterAlt
            )
            AppDropdownMenuAnchor(
                text = "Label",
                isExpanded = true,
                onClick = {},
                leadingIcon = Icons.Filled.FilterAlt
            )
            AppDropdownMenuAnchor(
                text = "Label",
                isExpanded = true,
                onClick = {},
                modifier = Modifier.width(100.dp),
                leadingIcon = Icons.Filled.FilterAlt
            )
        }
    }
}
