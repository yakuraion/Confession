package pro.yakuraion.confession.data.repositories.converters

import pro.yakuraion.confession.data.persistence.preferences.models.LastConfessionModel
import pro.yakuraion.confession.data.utils.strToDate
import pro.yakuraion.confession.domain.models.LastConfession

class LastConfessionConverter {

    fun dataToDomain(data: LastConfessionModel): LastConfession {
        return LastConfession(
            date = strToDate(data.date),
            pakuta = data.pakuta,
            comment = data.comment,
        )
    }
}
