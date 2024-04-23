package pro.yakuraion.parish.di.data.persistence

import androidx.room.Room
import org.koin.dsl.module
import pro.yakuraion.parish.data.persistence.AppDatabase

val fakePersistenceModule = module {
    single {
        Room
            .inMemoryDatabaseBuilder(get(), AppDatabase::class.java)
            .build()
    }
}
