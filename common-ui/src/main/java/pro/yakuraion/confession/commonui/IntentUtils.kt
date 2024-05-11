package pro.yakuraion.confession.commonui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

fun Context.openLink(uri: Uri) {
    val intent = Intent(Intent.ACTION_VIEW, uri)
    startActivity(intent)
}

fun Context.openNetworkSettings() {
    val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
    startActivity(intent)
}
