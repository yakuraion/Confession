package pro.yakuraion.confession.domain.usecases

import kotlinx.coroutines.flow.Flow
import pro.yakuraion.confession.domain.repositories.LastConfessionRepository

class GetPakutaCheckedUseCase(
    private val lastConfessionRepository: LastConfessionRepository,
) {

    fun invoke(): Flow<Boolean> {
        return lastConfessionRepository.getPakutaChecked()
    }
}
