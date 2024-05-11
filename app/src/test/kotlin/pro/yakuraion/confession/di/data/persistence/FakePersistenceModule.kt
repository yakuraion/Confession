package pro.yakuraion.confession.di.data.persistence

import androidx.room.Room
import org.koin.dsl.module
import pro.yakuraion.confession.data.persistence.AppDatabase

val fakePersistenceModule = module {
    single {
        Room
            .inMemoryDatabaseBuilder(get(), AppDatabase::class.java)
            .build()
    }
}
