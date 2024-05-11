package pro.yakuraion.confession.home.components.lastconfession

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pro.yakuraion.confession.commonui.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
fun HomeLastConfessionCard(
    now: LocalDate,
    lastDate: LocalDate,
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
            Spacer(modifier = Modifier.height(16.dp))
            DateText(date = lastDate)
            Spacer(modifier = Modifier.height(16.dp))
            DaysAgoText(now = now, date = lastDate)
        }

    }
}

@Composable
private fun Title(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(id = R.string.home_last_confession_title),
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun DateText(
    date: LocalDate,
    modifier: Modifier = Modifier,
) {
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
    val text = date.format(formatter)
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium,
    )
}

@Composable
private fun DaysAgoText(
    now: LocalDate,
    date: LocalDate,
    modifier: Modifier = Modifier,
) {
    val weeks = ChronoUnit.WEEKS.between(date, now).toInt()
    val text = if (weeks == 0) {
        stringResource(id = R.string.home_last_confession_last_little)
    } else {
        val weeksPlural = pluralStringResource(id = R.plurals.weeks, count = weeks)
        stringResource(id = R.string.home_last_confession_last, weeks, weeksPlural)
    }
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge
    )
}
