package pro.yakuraion.confession.data.repositories

import kotlinx.coroutines.withContext
import pro.yakuraion.androidcommon.coroutines.Dispatchers
import pro.yakuraion.confession.data.repositories.converters.SinTopicConverter
import pro.yakuraion.confession.data.repositories.datasources.SinTopicsDataSource
import pro.yakuraion.confession.domain.models.SinTopic
import pro.yakuraion.confession.domain.repositories.SinTopicsRepository

class SinTopicsRepositoryImpl(
    private val dispatchers: Dispatchers,
    private val sinTopicsDataSource: SinTopicsDataSource,
    private val sinTopicConverter: SinTopicConverter,
) : SinTopicsRepository {

    override suspend fun getSinTopics(): List<SinTopic> {
        return withContext(dispatchers.ioDispatcher) {
            sinTopicsDataSource.getSinTopics().map { sinTopicConverter.dataToDomain(it) }
        }
    }
}
