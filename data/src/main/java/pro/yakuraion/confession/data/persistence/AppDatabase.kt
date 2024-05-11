package pro.yakuraion.confession.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pro.yakuraion.confession.data.persistence.entities.TestEntity

@Database(
    entities = [
        TestEntity::class,
    ],
    version = 1,
)
@TypeConverters(
)
abstract class AppDatabase : RoomDatabase() {
}
