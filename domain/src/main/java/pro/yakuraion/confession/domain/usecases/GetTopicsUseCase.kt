package pro.yakuraion.confession.domain.usecases

import pro.yakuraion.confession.domain.models.Topic

class GetTopicsUseCase {

    suspend fun invoke(): List<Topic> {
        return listOf(
            Topic(
                url = "https://www.youtube.com/watch?v=cRR9PVdWp3o",
            ),
            Topic(
                url = "https://www.youtube.com/watch?v=VjSBVBX4YGM"
            ),
            Topic(
                url = "https://www.youtube.com/watch?v=MQdQQBMFVd8"
            ),
            Topic(
                url = "https://www.youtube.com/watch?v=9yuLuvWrSSQ"
            ),
            Topic(
                url = "https://www.youtube.com/watch?v=Cx8FoKeCJBU"
            )
        )
    }
}
