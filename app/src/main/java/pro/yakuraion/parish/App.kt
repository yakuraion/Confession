package pro.yakuraion.parish

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pro.yakuraion.parish.di.appModule
import pro.yakuraion.parish.logs.TimberReleaseTree
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
            modules(appModule)
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
