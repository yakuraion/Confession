package pro.yakuraion.confession.domain.repositories

import pro.yakuraion.confession.domain.models.SinTopic

interface SinTopicsRepository {

    suspend fun getSinTopics(): List<SinTopic>
}
