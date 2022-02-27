package me.hectorhalpizar.util

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

actual class FileReader actual constructor(private val fileName: String) {
    actual fun read() : String {
        try {
            val file = File(fileName)
            val br = BufferedReader(InputStreamReader(FileInputStream(file), "UTF-8"))
            var st: String?
            var fileContent: String = ""
            while (br.readLine().also { st = it } != null) // Print the string
            {
                // println(st)
                fileContent += st
            }
            return fileContent
        } catch (e: Exception) {
            throw FileReaderException(cause = e)
        }
    }
}