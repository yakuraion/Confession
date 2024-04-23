package pro.yakuraion.parish.commonui.compose.mediaplayer

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@SuppressLint("ComposableNaming")
@Composable
fun Flow<Uri>.collectPlayAudio() {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    DisposableEffect(Unit) {
        var mediaPlayer: MediaPlayer? = null
        coroutineScope.launch {
            collect { uri ->
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(context, uri).apply {
                        start()
                        setOnCompletionListener {
                            mediaPlayer?.release()
                            mediaPlayer = null
                        }
                    }
                }
            }
        }
        onDispose {
            mediaPlayer?.release()
        }
    }
}
