package me.hectorhalpizar.core.nytimes.usecase

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import me.hectorhalpizar.core.nytimes.data.ArticleRepository
import me.hectorhalpizar.core.nytimes.domain.Section
import me.hectorhalpizar.testing.kotlin.coroutines.runBlockingTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue

class GetLocalTopStoriesUseCaseTest {
    private val local: ArticleRepository.Local = mockk(relaxed = true)
    private val test = GetLocalTopStoriesUseCase(local)

    @Test
    fun invoke_success_repeated_article_removed() = runBlockingTest {
        coEvery { local.getArticles(any()) } returns listOf(
            mockk(relaxed = true) { every { short_url } returns "article1" },
            mockk(relaxed = true),
            mockk(relaxed = true) { every { short_url } returns "article1" },
        )
        assertEquals(2, test(Section.ARTS).size)
    }

    @Test
    fun invoke_failed_fetching_local_top_stories() = runBlockingTest {
        assertFails {
            coEvery { local.getArticles(any()) } throws IllegalStateException("Unit Test Exception")
            test(Section.ARTS)
        }.let { result ->
            assertTrue(result is NyTimesEdgeCase.FailedFetchingLocalTopStories)
            assertTrue { result.cause is IllegalStateException }
        }
    }

    @Test
    fun invoke_no_top_stories_stored_on_the_device() = runBlockingTest {
        assertFails {
            coEvery { local.getArticles(any()) } returns listOf()
            test(Section.ARTS)
        }.let { result ->
            assertTrue(result is NyTimesEdgeCase.NoArticlesStoredLocallyAvailable)
        }
    }
}