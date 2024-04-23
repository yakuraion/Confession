package pro.yakuraion.parish.logs

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import timber.log.Timber

class TimberReleaseTree : Timber.Tree() {

    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR && t != null) {
            Log.e(tag, message, t)
            Firebase.crashlytics.recordException(t)
        }
    }
}
