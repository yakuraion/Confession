package pro.yakuraion.confession.commonui.compose.widgets.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import pro.yakuraion.confession.commonui.R

@Composable
fun GeneralErrorDialog(
    onDismissRequest: () -> Unit,
) {
    AppAlertDialog(
        onDismissRequest = onDismissRequest,
        title = stringResource(id = R.string.common_general_error_dialog_title),
        description = stringResource(id = R.string.common_general_error_dialog_description),
        primaryButtonText = stringResource(id = R.string.common_general_error_dialog_button_text),
        onPrimaryButtonClick = onDismissRequest,
    )
}
