package pro.yakuraion.confession.data.persistence.preferences.models

import com.google.gson.annotations.SerializedName

class LastConfessionModel(
    @SerializedName("date") val date: String,
    @SerializedName("pakuta") val pakuta: String,
    @SerializedName("comment") val comment: String,
)
