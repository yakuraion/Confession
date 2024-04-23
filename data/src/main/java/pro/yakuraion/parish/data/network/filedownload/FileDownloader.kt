package pro.yakuraion.parish.data.network.filedownload

import java.io.File
import java.net.URL

interface FileDownloader {

    fun downloadFile(url: URL, outputFile: File)
}
