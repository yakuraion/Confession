package pro.yakuraion.confession.home.components.nolastconfession

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pro.yakuraion.confession.commonui.R

@Composable
fun HomeCreateConfessionCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(20.dp)
    Column(
        modifier = modifier
            .background(Color(0x57200145), shape)
            .clip(shape)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CompositionLocalProvider(LocalContentColor provides Color.White) {
            Title()
        }

    }
}

@Composable
private fun Title(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(id = R.string.home_create_confession_card_title),
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge
    )
}
