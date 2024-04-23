package pro.yakuraion.androidcommon.network

import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL

private const val DEFAULT_BUFFER_SIZE = 1024

fun downloadFile(url: URL, outputFile: File, bufferSize: Int = DEFAULT_BUFFER_SIZE) {
    url.openConnection().connect()
    val input = BufferedInputStream(url.openStream())

    with(outputFile) {
        parentFile?.mkdirs()
        createNewFile()
    }

    val output = FileOutputStream(outputFile)
    val data = ByteArray(bufferSize)

    var count = input.read(data)
    while (count != -1) {
        output.write(data, 0, count)
        count = input.read(data)
    }

    output.flush()
    output.close()
    input.close()
}
