package pro.yakuraion.androidcommon.mockwebserverwrapper

import okhttp3.mockwebserver.RecordedRequest

fun interface MockWebServerCondition {

    fun condition(request: RecordedRequest): Boolean
}
