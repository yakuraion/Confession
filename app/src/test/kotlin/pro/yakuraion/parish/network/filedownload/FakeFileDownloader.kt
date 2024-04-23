package pro.yakuraion.parish.network.filedownload

import pro.yakuraion.parish.data.network.filedownload.FileDownloader
import java.io.File
import java.net.URL

class FakeFileDownloader : FileDownloader {

    override fun downloadFile(url: URL, outputFile: File) {
        // empty
    }
}
