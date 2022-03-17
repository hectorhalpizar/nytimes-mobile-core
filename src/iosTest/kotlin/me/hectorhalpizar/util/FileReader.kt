package me.hectorhalpizar.util

import kotlinx.cinterop.ByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.fclose
import platform.posix.fgets
import platform.posix.fopen

actual class FileReader actual constructor(private val fileName: String) {
    actual fun read(): String {
        try {
            val returnBuffer = StringBuilder()
            val file = fopen(fileName, "r") ?: throw IllegalArgumentException("Cannot open input file $fileName")
            memScoped {
                try {
                    val readBufferLength = 64 * 1024
                    val buffer = allocArray<ByteVar>(readBufferLength)
                    var line = fgets(buffer, readBufferLength, file)?.toKString()
                    while (line != null) {
                        returnBuffer.append(line)
                        line = fgets(buffer, readBufferLength, file)?.toKString()
                    }
                } finally {
                    fclose(file)
                }
            }
            return returnBuffer.toString()
        } catch (e: Exception) {
            throw FileReaderException(cause = e)
        }
    }
}