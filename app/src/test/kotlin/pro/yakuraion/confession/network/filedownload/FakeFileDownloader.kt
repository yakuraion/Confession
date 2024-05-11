package pro.yakuraion.confession.network.filedownload

import pro.yakuraion.confession.data.network.filedownload.FileDownloader
import java.io.File
import java.net.URL

class FakeFileDownloader : FileDownloader {

    override fun downloadFile(url: URL, outputFile: File) {
        // empty
    }
}
