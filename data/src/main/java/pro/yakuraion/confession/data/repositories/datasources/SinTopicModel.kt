package pro.yakuraion.confession.data.repositories.datasources

import com.google.gson.annotations.SerializedName

class SinTopicModel(
    @SerializedName("title") val title: String,
    @SerializedName("list") val questions: List<String>,
)
