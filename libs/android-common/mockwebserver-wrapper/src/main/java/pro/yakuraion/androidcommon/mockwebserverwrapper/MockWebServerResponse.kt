package pro.yakuraion.androidcommon.mockwebserverwrapper

import okhttp3.mockwebserver.MockResponse
import java.io.InputStream
import java.net.HttpURLConnection

sealed class MockWebServerResponse {

    abstract fun toMockResponse(): MockResponse

    open class Body(private val body: String) : MockWebServerResponse() {

        override fun toMockResponse(): MockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(body)
    }

    open class InputStreamBody(private val stream: InputStream) : MockWebServerResponse() {

        override fun toMockResponse(): MockResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(stream.bufferedReader().use { it.readText() })
    }

    open class EmptyWithCode(private val code: Int) : MockWebServerResponse() {

        override fun toMockResponse(): MockResponse = MockResponse()
            .setResponseCode(code)
    }

    object EmptyOK : EmptyWithCode(HttpURLConnection.HTTP_OK)

    object NotFound : EmptyWithCode(HttpURLConnection.HTTP_NOT_FOUND)
}
