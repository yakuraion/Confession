package pro.yakuraion.confession.data.repositories.converters

import pro.yakuraion.confession.data.repositories.datasources.SinTopicModel
import pro.yakuraion.confession.domain.models.SinTopic

class SinTopicConverter {

    fun dataToDomain(data: SinTopicModel): SinTopic {
        return SinTopic(
            title = data.title,
            questions = data.questions.map { SinTopic.Question(it) }
        )
    }
}
