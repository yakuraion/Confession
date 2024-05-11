package pro.yakuraion.confession.newconfession.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pro.yakuraion.confession.commonui.R
import pro.yakuraion.confession.newconfession.NewConfessionDialogsState
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Composable
fun NewConfessionDialogs(
    dialogsState: NewConfessionDialogsState,
    onDatePickerDialogDismissRequest: () -> Unit,
    onDatePickerDialogDateSelect: (LocalDate) -> Unit,
) {
    when (dialogsState) {
        is NewConfessionDialogsState.None -> Unit
        is NewConfessionDialogsState.DatePicker -> {
            val datePickerState = rememberDatePickerState()
            DatePickerDialog(
                onDismissRequest = onDatePickerDialogDismissRequest,
                confirmButton = {
                    Text(
                        text = stringResource(id = R.string.button_confirm),
                        modifier = Modifier
                            .clickable {
                                val millis = datePickerState.selectedDateMillis ?: return@clickable
                                // todo подумать про смену системной зоны
                                val date = Instant
                                    .ofEpochMilli(millis)
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                                onDatePickerDialogDateSelect.invoke(date)
                            }
                            .padding(8.dp),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    dateValidator = { millis ->
                        val localDate = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
                        !LocalDate.now().isBefore(localDate)
                    },
                    title = null,
                    headline = null,
                    showModeToggle = false,
                )
            }
        }
    }
}
