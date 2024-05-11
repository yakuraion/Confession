package pro.yakuraion.confession.domain.usecases

import pro.yakuraion.confession.domain.repositories.LastConfessionRepository

class SetPakutaCheckedUseCase(
    private val lastConfessionRepository: LastConfessionRepository,
) {

    suspend fun invoke(checked: Boolean) {
        lastConfessionRepository.setPakutaChecked(checked)
    }
}
