package pro.yakuraion.confession.newconfession

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.yakuraion.destinationscompose.core.DestinationScreen
import org.koin.androidx.compose.koinViewModel
import pro.yakuraion.confession.commonui.R
import pro.yakuraion.confession.commonui.compose.coroutines.collectInLaunchedEffect
import pro.yakuraion.confession.commonui.compose.theme.AppTheme
import pro.yakuraion.confession.commonui.compose.widgets.textfields.AppOutlinedTextField
import pro.yakuraion.confession.newconfession.components.NewConfessionDialogs
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@DestinationScreen
@Composable
fun NewConfessionScreen(
    onBackRequest: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: NewConfessionViewModel = koinViewModel(),
) {
    viewModel.onSuccess.collectInLaunchedEffect {
        onSuccess.invoke()
    }

    NewConfessionScreen(
        state = viewModel.state,
        onCloseClick = onBackRequest,
        onDateClick = viewModel::onDateClick,
        onPakutaChange = viewModel::onPakutaChange,
        onCommentChange = viewModel::onCommentChange,
        onContinueClick = viewModel::onContinueClick,
        dialogsState = viewModel.dialogsState,
        onDatePickerDialogDismissRequest = viewModel::onDatePickerDialogDismissRequest,
        onDatePickerDialogDateSelect = viewModel::onDatePickerDialogDateSelect,
    )
}

@Composable
private fun NewConfessionScreen(
    state: NewConfessionState,
    onCloseClick: () -> Unit,
    onDateClick: () -> Unit,
    onPakutaChange: (pakuta: String) -> Unit,
    onCommentChange: (comment: String) -> Unit,
    onContinueClick: () -> Unit,
    dialogsState: NewConfessionDialogsState,
    onDatePickerDialogDismissRequest: () -> Unit,
    onDatePickerDialogDateSelect: (LocalDate) -> Unit,
) {
    Content(
        state = state,
        onCloseClick = onCloseClick,
        onDateClick = onDateClick,
        onPakutaChange = onPakutaChange,
        onCommentChange = onCommentChange,
        onContinueClick = onContinueClick,
    )

    NewConfessionDialogs(
        dialogsState = dialogsState,
        onDatePickerDialogDismissRequest = onDatePickerDialogDismissRequest,
        onDatePickerDialogDateSelect = onDatePickerDialogDateSelect,
    )
}

@Composable
private fun Content(
    state: NewConfessionState,
    onCloseClick: () -> Unit,
    onDateClick: () -> Unit,
    onPakutaChange: (pakuta: String) -> Unit,
    onCommentChange: (comment: String) -> Unit,
    onContinueClick: () -> Unit,
) {
    val topPadding = with(LocalDensity.current) {
        WindowInsets.systemBars.getTop(this).toDp()
    }
    val bottomPadding = with(LocalDensity.current) {
        WindowInsets.systemBars.getBottom(this).toDp()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawBackground()
            .padding()
            .imePadding()
    ) {
        Spacer(modifier = Modifier.height(topPadding))

        Spacer(modifier = Modifier.height(20.dp))

        TopBar(
            onCloseClick = onCloseClick,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(18.dp))

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            Title(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
            )

            Spacer(modifier = Modifier.height(26.dp))

            DateField(
                date = state.date,
                onClick = onDateClick,
            )

            Spacer(modifier = Modifier.height(18.dp))

            PakutaField(
                pakuta = state.pakuta,
                onPakutaChange = onPakutaChange,
            )

            Spacer(modifier = Modifier.height(18.dp))

            CommentField(
                comment = state.comment,
                onCommentChange = onCommentChange,
            )

            Spacer(modifier = Modifier.height(32.dp))

            ContinueButton(
                onClick = onContinueClick,
                isEnabled = state.isContinueEnabled,
                isLoading = state.isContinueLoading,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(bottomPadding))
        }
    }
}

@Composable
private fun TopBar(
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.padding(end = 20.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        IconButton(
            onClick = onCloseClick,
            modifier = Modifier
                .size(42.dp)
                .background(Color(0x38202020), CircleShape),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun Title(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.new_confession_title),
        modifier = modifier,
        color = Color.White,
        style = MaterialTheme.typography.headlineMedium,
    )
}

@Composable
private fun DateField(
    date: LocalDate?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val formatter = DateTimeFormatter.ofPattern("dd/LL/yyyy")
    val dateString = date?.format(formatter).orEmpty()

    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        Text(
            text = stringResource(id = R.string.new_confession_date_label),
            modifier = Modifier.padding(start = 20.dp),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(12.dp))

        val interactionSource = remember { MutableInteractionSource() }
        AppOutlinedTextField(
            value = dateString,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable(interactionSource = interactionSource, indication = null) { onClick.invoke() },
            enabled = false,
            placeholder = stringResource(id = R.string.new_confession_date_placeholder)
        )
    }
}

@Composable
private fun PakutaField(
    pakuta: String,
    onPakutaChange: (pakuta: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        Text(
            text = stringResource(id = R.string.new_confession_pakuta_label),
            modifier = Modifier.padding(start = 20.dp),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(12.dp))
        AppOutlinedTextField(
            value = pakuta,
            onValueChange = onPakutaChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = stringResource(id = R.string.new_confession_pakuta_placeholder),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            singleLine = true,
            maxLines = 1,
        )
    }
}

@Composable
private fun CommentField(
    comment: String,
    onCommentChange: (comment: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        Text(
            text = stringResource(id = R.string.new_confession_comment_label),
            modifier = Modifier.padding(start = 20.dp),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(12.dp))
        AppOutlinedTextField(
            value = comment,
            onValueChange = onCommentChange,
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
        )
    }
}

@Composable
private fun ContinueButton(
    onClick: () -> Unit,
    isEnabled: Boolean,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(42.dp)
            .alpha(if (isEnabled) 1f else 0.5f),
        enabled = isEnabled && !isLoading,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0x3BFFFFFF),
            contentColor = Color.White,
            disabledContainerColor = Color(0x3BFFFFFF),
            disabledContentColor = Color.White,
        )
    ) {
        Text(
            text = stringResource(id = R.string.new_confession_continue_button),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

private fun Modifier.drawBackground(): Modifier {
    return drawBehind {
        drawRect(
            backgroundBrush(
                start = Offset(0f, size.height),
                end = Offset(size.width, 0f)
            ),
            size = size,
        )
    }
}

private fun backgroundBrush(
    start: Offset,
    end: Offset,
): Brush {
    return Brush.linearGradient(
        0f to Color(0xFF5D03CF),
        0.39f to Color(0xFF7400E9),
        0.68f to Color(0xFF9D20FF),
        0.88f to Color(0xFFEC79FF),
        1f to Color(0xFFFF98E3),
        start = start,
        end = end,
    )
}

@Preview
@Composable
private fun Preview() {
    val state = NewConfessionState().apply {
        date = LocalDate.of(2024, 1, 1)
        pakuta = "Ойча Наш x3"
        comment = "Какой-то коммент"
    }
    AppTheme {
        NewConfessionScreen(
            state = state,
            onCloseClick = {},
            onDateClick = {},
            onPakutaChange = {},
            onCommentChange = {},
            onContinueClick = {},
            dialogsState = NewConfessionDialogsState.None,
            onDatePickerDialogDismissRequest = {},
            onDatePickerDialogDateSelect = {},
        )
    }
}
