package pro.yakuraion.androidcommon.mockwebserverwrapper

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

class MockWebServerWrapper(
    val server: MockWebServer = MockWebServer(),
    private val defaultResponse: MockWebServerResponse = MockWebServerResponse.NotFound,
) {

    private val rules: MutableList<Rule> = mutableListOf()

    init {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                val response = rules.reversed()
                    .firstOrNull { it.condition.condition(request) }
                    ?.response ?: defaultResponse
                return response.toMockResponse()
            }
        }
    }

    fun whenRequest(condition: MockWebServerCondition): ConditionScope = ConditionScope(condition)

    fun whenRequestPathContains(part: String): ConditionScope {
        return whenRequest { it.path?.contains(part) == true }
    }

    inner class ConditionScope(private val condition: MockWebServerCondition) {

        infix fun returns(response: MockWebServerResponse) {
            val rule = Rule(condition, response)
            rules.add(rule)
        }
    }

    inner class Rule(val condition: MockWebServerCondition, val response: MockWebServerResponse)
}
