package pro.yakuraion.confession.utils

import android.content.Context
import pro.yakuraion.androidcommon.mockwebserverwrapper.MockWebServerResponse

fun getResourcesMockServerResponse(name: String): MockWebServerResponse {
    val stream = Context::class.java.classLoader!!.getResourceAsStream("network/responses/$name")
    return MockWebServerResponse.InputStreamBody(stream)
}

@Suppress("unused")
object EmptyJsonListMockWebServerResponse : MockWebServerResponse.Body("[]")
