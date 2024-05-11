package pro.yakuraion.confession.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.yakuraion.confession.commonui.R

@Composable
fun HomeScreenTopBar(
    onCalendarClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.defaultMinSize(minHeight = 64.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CalendarIcon(onClick = onCalendarClick)
        Spacer(modifier = Modifier.width(8.dp))
        NotificationsIcon(onClick = onNotificationsClick)
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Composable
private fun CalendarIcon(
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Image(
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
    }
}

@Composable
private fun NotificationsIcon(
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Image(
            painter = painterResource(id = R.drawable.ic_doorbell),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
    }
}
