package pro.yakuraion.confession

import android.graphics.drawable.ColorDrawable
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.squareup.seismic.ShakeDetector
import pro.yakuraion.confession.commonui.compose.theme.AppTheme
import pro.yakuraion.confession.debugging.ui.DebuggingScreen
import pro.yakuraion.confession.main.ui.MainScreen

class AppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (getIsProdReleaseBuild()) {
            setContent()
        } else {
            setDebuggableContent()
        }
    }

    @Suppress("KotlinConstantConditions")
    private fun getIsProdReleaseBuild(): Boolean {
        return !BuildConfig.DEBUG && BuildConfig.FLAVOR == "prod"
    }

    private fun setContent() {
        setContent {
            AppTheme {
                SetUpStatusBarColorEffect()
                SetUpActivityBackgroundEffect()
                MainScreen()
            }
        }
    }

    private fun setDebuggableContent() {
        setContent {
            var isDebugging by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                doOnShake { isDebugging = true }
            }
            AppTheme {
                SetUpStatusBarColorEffect()
                SetUpActivityBackgroundEffect()
                if (isDebugging) {
                    DebuggingScreen(onBackRequest = { isDebugging = false })
                } else {
                    MainScreen()
                }
            }
        }
    }

    private fun doOnShake(action: () -> Unit) {
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val shakeDetector = ShakeDetector { action() }
        shakeDetector.start(sensorManager)
    }

    @Composable
    private fun SetUpStatusBarColorEffect() {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()
        val backgroundColor = MaterialTheme.colorScheme.surface

        LaunchedEffect(systemUiController, useDarkIcons, backgroundColor) {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
        }
    }

    @Composable
    private fun SetUpActivityBackgroundEffect() {
        val backgroundColor = MaterialTheme.colorScheme.surface

        LaunchedEffect(backgroundColor) {
            val drawable = ColorDrawable(backgroundColor.toArgb())
            window.setBackgroundDrawable(drawable)
        }
    }
}
