package pro.yakuraion.confession.data.network.filedownload

import java.io.File
import java.net.URL

internal class RealFileDownloader : FileDownloader {

    override fun downloadFile(url: URL, outputFile: File) {
        downloadFile(url, outputFile)
    }
}
