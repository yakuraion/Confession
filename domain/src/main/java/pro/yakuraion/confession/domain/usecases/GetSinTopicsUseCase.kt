package pro.yakuraion.confession.domain.usecases

import pro.yakuraion.confession.domain.models.SinTopic
import pro.yakuraion.confession.domain.repositories.SinTopicsRepository

class GetSinTopicsUseCase(
    private val sinTopicsRepository: SinTopicsRepository,
) {

    suspend fun invoke(): List<SinTopic> {
        return sinTopicsRepository.getSinTopics()
    }
}
