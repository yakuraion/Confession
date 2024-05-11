package pro.yakuraion.confession.domain.repositories

import kotlinx.coroutines.flow.Flow
import pro.yakuraion.confession.domain.models.LastConfession
import java.time.LocalDate

interface LastConfessionRepository {

    fun getLastConfession(): Flow<LastConfession?>

    suspend fun setLastConfession(
        date: LocalDate,
        pakuta: String,
        comment: String,
    )
}
