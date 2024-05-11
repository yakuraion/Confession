package pro.yakuraion.confession.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pro.yakuraion.confession.commonui.R

@Composable
fun NavigationIcon(
    @DrawableRes iconRes: Int,
    size: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(42.dp)
            .background(Color(0x38202020), CircleShape),
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(size),
        )
    }
}

@Composable
fun NavigationIconClose(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationIcon(
        iconRes = R.drawable.ic_close,
        size = 18.dp,
        onClick = onClick,
        modifier = modifier,
    )
}

@Composable
fun NavigationIconBack(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationIcon(
        iconRes = R.drawable.ic_arrow_back,
        size = 29.dp,
        onClick = onClick,
        modifier = modifier,
    )
}
