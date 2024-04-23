package pro.yakuraion.androidcommon.kotlin

inline fun <T> T.applyIf(condition: Boolean, statement: T.() -> T): T {
    if (condition) {
        return statement()
    }
    return this
}
