package pro.yakuraion.confession.commonui

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import kotlin.math.pow
import kotlin.math.roundToInt

fun Context.getVibrator(): Vibrator {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
}

fun Vibrator.vibrateError() {
    val millis = 300L
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrate(VibrationEffect.createOneShot(millis, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        //deprecated in API 26
        vibrate(millis)
    }
}

fun Vibrator.vibrateSlowRise(millis: Long, scale: Float = 1f) {
    val timingInterval = 50L
    val maxAmplitude = 255
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val numberOfTimings = millis / timingInterval
        val timings = (0..numberOfTimings).map { it * timingInterval }.toLongArray()
        val amplitudes = (0..numberOfTimings).map { timingIndex ->
            val timePart = timingIndex / numberOfTimings.toDouble()
            (timePart.pow(2.0) * maxAmplitude * scale).roundToInt()
        }.toIntArray()
        vibrate(VibrationEffect.createWaveform(timings, amplitudes, -1))
    }
}
