package pro.yakuraion.confession.domain.usecases

import kotlinx.coroutines.flow.Flow
import pro.yakuraion.confession.domain.models.LastConfession
import pro.yakuraion.confession.domain.repositories.LastConfessionRepository

class GetLastConfessionUseCase(
    private val lastConfessionRepository: LastConfessionRepository,
) {

    fun invoke(): Flow<LastConfession?> {
        return lastConfessionRepository.getLastConfession()
    }
}
