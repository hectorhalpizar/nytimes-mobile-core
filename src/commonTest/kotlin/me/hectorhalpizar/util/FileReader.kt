package me.hectorhalpizar.util

expect class FileReader(fileName: String) {
    /**
     * Reads a given file and returns the string as UTF-8
     */
    fun read() : String
}