package pro.yakuraion.parish.commontestsui

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import pro.yakuraion.parish.commonui.compose.theme.AppTheme

open class ComposeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    protected fun setScreen(screen: @Composable () -> Unit) {
        composeTestRule.setContent {
            AppTheme {
                screen()
            }
        }
    }
}
