package pro.yakuraion.confession

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pro.yakuraion.confession.di.appModule
import pro.yakuraion.confession.di.mainModule
import pro.yakuraion.confession.logs.TimberReleaseTree
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        setUpKoin()
    }

    private fun setUpKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(
                appModule,
                mainModule,
            )
        }
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(TimberReleaseTree())
        }
    }
}
