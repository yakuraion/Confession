package pro.yakuraion.confession.domain.models

data class SinTopic(
    val title: String,
    val questions: List<Question>,
) {

    class Question(val text: String)
}
