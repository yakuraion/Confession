package pro.yakuraion.confession.data.repositories.datasources

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pro.yakuraion.confession.data.R
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.io.Writer
import java.lang.reflect.Type

class SinTopicsDataSource(
    private val context: Context,
    private val gson: Gson,
) {

    fun getSinTopics(): List<SinTopicModel> {
        val inputStream: InputStream = context.resources.openRawResource(R.raw.sins)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        inputStream.use {
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        val jsonString = writer.toString()

        val listType: Type = object : TypeToken<ArrayList<SinTopicModel>>() {}.type
        val array: ArrayList<SinTopicModel> = gson.fromJson(jsonString, listType)
        return array.toList()
    }
}
