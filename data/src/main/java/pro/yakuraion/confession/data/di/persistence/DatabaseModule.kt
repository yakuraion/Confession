package pro.yakuraion.confession.data.di.persistence

import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module
import pro.yakuraion.confession.data.persistence.AppDatabase

private const val DATABASE_NAME = "database"

internal val databaseModule = module {
    installDatabase()
    installDao()
}

private fun Module.installDatabase() {
    single {
        Room
            .databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME)
            .build()
    } bind RoomDatabase::class
}

private fun Module.installDao() {
}
