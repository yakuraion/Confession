package pro.yakuraion.confession.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import pro.yakuraion.confession.commonui.R
import pro.yakuraion.confession.commonui.compose.widgets.buttons.AppCheckbox

@Composable
fun HomePakutaCard(
    pakutaText: String,
    checked: Boolean,
    onCheckedChange: (isChecked: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(20.dp)
    Column(
        modifier = modifier
            .background(Color(0x57200145), shape)
            .padding(horizontal = 16.dp, vertical = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CompositionLocalProvider(LocalContentColor provides Color.White) {
            Text(
                text = stringResource(id = R.string.home_pakuta_title),
                style = MaterialTheme.typography.titleLarge,
            )

            Spacer(modifier = Modifier.height(16.dp))

            CheckboxRow(
                pakutaText = pakutaText,
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun CheckboxRow(
    pakutaText: String,
    checked: Boolean,
    onCheckedChange: (isChecked: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AppCheckbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = pakutaText,
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
        )
    }
}
