package pro.yakuraion.confession.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.yakuraion.confession.commonui.R

@Composable
fun HomeQuoteBlock(
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalContentColor provides Color(0xFF460886)) {
        Box(modifier = modifier) {
            Image(modifier = Modifier.fillMaxWidth())

            QuoteText(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            )
        }
    }
}

@Composable
private fun Image(modifier: Modifier = Modifier) {
    val density = LocalDensity.current
    val heightOfBottomBar = with(LocalDensity.current) { 80.dp.toPx() }
    val insetsBottom = WindowInsets.systemBars.getBottom(density)
    val height = heightOfBottomBar + insetsBottom

    androidx.compose.foundation.Image(
        painter = painterResource(id = R.drawable.il_home_background),
        contentDescription = null,
        modifier = modifier.graphicsLayer {
            translationY = height
        },
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun QuoteText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 64.dp),
    ) {
        Text(
            text = """
            “Калі ты кожны дзень п'еш атруту грахоў,
            то кожны дзень павінен
            прымаць проціяддзе споведзі”
        """.trimIndent(),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Антоній Падуанскі",
            modifier = Modifier.align(Alignment.End),
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.height(60.dp))
    }
}
