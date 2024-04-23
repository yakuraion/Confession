package pro.yakuraion.parish.data.persistence.typeconverters

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateTypeConverter {

    @TypeConverter
    fun localDateToLong(localDate: LocalDate?): Long? {
        return localDate?.toEpochDay()
    }

    @TypeConverter
    fun longToLocalDate(long: Long?): LocalDate? {
        return long?.let { LocalDate.ofEpochDay(it) }
    }
}
