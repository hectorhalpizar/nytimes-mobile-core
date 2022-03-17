package me.hectorhalpizar.core.nytimes

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.hectorhalpizar.PROJECT_PATH
import me.hectorhalpizar.core.nytimes.domain.Result
import me.hectorhalpizar.util.FileReader
import kotlin.test.Test
import kotlin.test.assertEquals

class ResultDeserializationTest {
    @Test
    fun deserialize_result() {
        val json = FileReader("${PROJECT_PATH}/feed_top_stories.json").read()
        Json.decodeFromString<Result>(json).let { result ->
            assertEquals(39, result.num_results)
            assertEquals(39, result.results.size)

            result.results.first().let { firstArticle ->
                assertEquals("‘Pam & Tommy’: A Story of Sex, Crimes and Videotape", firstArticle.title)
                // 2022-02-02T05:00:31-05:00
                firstArticle.created_date.toLocalDateTime(TimeZone.UTC).let { createdDate ->
                    assertEquals(2022, createdDate.year)
                    assertEquals(2, createdDate.monthNumber)
                    assertEquals(2, createdDate.dayOfMonth)
                    assertEquals(10, createdDate.hour)
                    assertEquals(0, createdDate.minute)
                    assertEquals(31, createdDate.second)
                }
                assertEquals("https://nyti.ms/3rj1XLJ", firstArticle.short_url)
            }
        }
    }
}