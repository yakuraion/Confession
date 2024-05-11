package pro.yakuraion.confession.domain.usecases

import pro.yakuraion.confession.domain.repositories.LastConfessionRepository
import java.time.LocalDate

class SetNewConfessionUseCase(
    private val lastConfessionRepository: LastConfessionRepository,
) {

    suspend fun invoke(
        date: LocalDate,
        pakuta: String,
        comment: String,
    ) {
        lastConfessionRepository.setLastConfession(
            date = date,
            pakuta = pakuta,
            comment = comment,
        )
    }
}
