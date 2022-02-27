package me.hectorhalpizar.util

actual class FileReader actual constructor(private val fileName: String) {
    actual fun read() : String {
        try {
            TODO("")
        } catch (e: Exception) {
            throw FileReaderException(cause = e.cause)
        }
    }
}