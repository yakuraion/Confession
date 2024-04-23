package pro.yakuraion.parish.commonui.compose.widgets.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import pro.yakuraion.parish.commonui.R

@Composable
fun AppTopBarIconBack(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(id = R.string.top_bar_icon_back_content_description),
) {
    AppTopBarIcon(
        imageVector = Icons.Filled.ArrowBack,
        onClick = onClick,
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Composable
fun AppTopBarIconClose(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(id = R.string.top_bar_icon_close_content_description),
) {
    AppTopBarIcon(
        imageVector = Icons.Filled.Close,
        onClick = onClick,
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Composable
fun AppTopBarIconSettings(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(id = R.string.top_bar_icon_settings_content_description),
) {
    AppTopBarIcon(
        imageVector = Icons.Filled.Settings,
        onClick = onClick,
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Composable
fun AppTopBarIconSearch(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(id = R.string.top_bar_icon_search_content_description),
) {
    AppTopBarIcon(
        imageVector = Icons.Filled.Search,
        onClick = onClick,
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Composable
fun AppTopBarIcon(
    imageVector: ImageVector,
    onClick: () -> Unit,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}
