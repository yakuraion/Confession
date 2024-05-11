package pro.yakuraion.confession.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import pro.yakuraion.androidcommon.coroutines.Dispatchers
import pro.yakuraion.confession.data.persistence.preferences.LastConfessionPreferences
import pro.yakuraion.confession.data.persistence.preferences.models.LastConfessionModel
import pro.yakuraion.confession.data.repositories.converters.LastConfessionConverter
import pro.yakuraion.confession.data.utils.dateToStr
import pro.yakuraion.confession.domain.models.LastConfession
import pro.yakuraion.confession.domain.repositories.LastConfessionRepository
import java.time.LocalDate

class LastConfessionRepositoryImpl(
    private val dispatchers: Dispatchers,
    private val lastConfessionConverter: LastConfessionConverter,
    private val lastConfessionPreferences: LastConfessionPreferences,
) : LastConfessionRepository {

    override fun getLastConfession(): Flow<LastConfession?> {
        return lastConfessionPreferences.lastConfession
            .map { data -> data?.let { lastConfessionConverter.dataToDomain(it) } }
            .flowOn(dispatchers.ioDispatcher)
    }

    override suspend fun setLastConfession(date: LocalDate, pakuta: String, comment: String) {
        withContext(dispatchers.ioDispatcher) {
            val data = LastConfessionModel(
                date = dateToStr(date),
                pakuta = pakuta,
                comment = comment,
            )
            lastConfessionPreferences.setLastConfession(data)
        }
    }
}
