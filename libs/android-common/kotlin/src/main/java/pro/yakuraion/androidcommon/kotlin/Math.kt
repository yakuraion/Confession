package pro.yakuraion.androidcommon.kotlin

infix fun Int.pow(x: Int): Int = this.toBigInteger().pow(x).toInt()
