package pro.yakuraion.plugins.utils

import org.gradle.api.Project
import org.gradle.internal.os.OperatingSystem
import java.io.File

fun Project.openFileByOS(file: File) {
    val currentOS = OperatingSystem.current()
    val args = when {
        currentOS.isMacOsX -> listOf("open")
        currentOS.isLinux -> listOf("xdg-open")
        currentOS.isWindows -> listOf("cmd", "/c", "start")
        else -> error("Unsupported OS: ${currentOS.name}")
    } + listOf(file.absolutePath)
    exec {
        commandLine(args)
    }
}
