package pro.yakuraion.confession.domain.repositories

import kotlinx.coroutines.flow.Flow
import pro.yakuraion.confession.domain.models.LastConfession
import java.time.LocalDate

interface LastConfessionRepository {

    fun getLastConfession(): Flow<LastConfession?>

    fun getPakutaChecked(): Flow<Boolean>

    suspend fun setLastConfession(
        date: LocalDate,
        pakuta: String,
        comment: String,
    )

    suspend fun setPakutaChecked(checked: Boolean)
}
