package me.hectorhalpizar.core.nytimes.usecase

import me.hectorhalpizar.core.nytimes.domain.Article

/**
 * will be used to detect our Edge Cases for any [BaseUseCase]
 *
 * @param message Short message for the edge case
 * @param cause Possible cause on why this Edge Case was caused
 */
sealed class NyTimesEdgeCase(message: String?, cause: Throwable?) : Exception(message, cause) {
    class FailedFetchingRemoteTopStories(cause: Throwable?) : NyTimesEdgeCase("Failed fetching stories from the Network", cause)
    class FailedStoringTopStoryLocally(topStoryToStore: Article, cause: Throwable?) : NyTimesEdgeCase("Failed to store top story $topStoryToStore", cause)
}