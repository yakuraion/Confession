package pro.yakuraion.parish.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pro.yakuraion.parish.data.persistence.entities.ParishEntity

@Database(
    entities = [
        ParishEntity::class,
    ],
    version = 1,
)
@TypeConverters(
)
abstract class AppDatabase : RoomDatabase() {
}
