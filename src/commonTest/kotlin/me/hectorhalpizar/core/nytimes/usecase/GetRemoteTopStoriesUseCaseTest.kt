package me.hectorhalpizar.core.nytimes.usecase

import io.mockk.coEvery
import io.mockk.mockk
import me.hectorhalpizar.core.nytimes.data.ArticleRepository
import me.hectorhalpizar.core.nytimes.domain.Section
import me.hectorhalpizar.testing.kotlin.coroutines.runBlockingTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertTrue

class GetRemoteTopStoriesUseCaseTest {
    private val local: ArticleRepository.Local = mockk(relaxed = true)
    private val remote: ArticleRepository.Remote = mockk(relaxed = true)
    private val test = GetRemoteTopStoriesUseCase(remote, local)

    @Test
    fun invoke_success() = runBlockingTest {
        coEvery { remote.getArticles(any()) } returns listOf(
            mockk(relaxed = true)
        )
        coEvery { local.storeTopStory(any()) } returns Unit
        assertEquals(Unit, test(Section.ARTS))
    }

    @Test
    fun invoke_failed_storing_top_story_locally() = runBlockingTest {
        assertFails {
            coEvery { remote.getArticles(any()) } returns listOf(
                mockk(relaxed = true)
            )
            coEvery { local.storeTopStory(any()) } throws IllegalStateException("Unit Test Exception")
            test(Section.ARTS)
        }.let { result ->
            assertTrue(result is NyTimesEdgeCase.FailedStoringTopStoryLocally)
            assertTrue(result.cause is IllegalStateException)
        }
    }

    @Test
    fun invoke_failed_fetching_top_stories() = runBlockingTest {
        assertFails {
            coEvery { remote.getArticles(any()) } throws IllegalStateException("Unit Test Exception")
            test(Section.ARTS)
        }.let { result ->
            assertTrue(result is NyTimesEdgeCase.FailedFetchingRemoteTopStories)
            assertTrue(result.cause is IllegalStateException)
        }
    }
}