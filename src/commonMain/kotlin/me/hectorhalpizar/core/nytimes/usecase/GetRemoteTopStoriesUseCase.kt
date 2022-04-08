package me.hectorhalpizar.core.nytimes.usecase

import me.hectorhalpizar.core.nytimes.data.ArticleRepository
import me.hectorhalpizar.core.nytimes.domain.Section

/**
 * Gets all the articles from the NY Time Rest API and stores in the device
 *
 * @param remoteRepository [ArticleRepository.Remote] will extract the information from a remote server
 * @param localRepository [ArticleRepository.Local] will save the stories to the current device
 *
 * @throws [NyTimesEdgeCase.FailedFetchingRemoteTopStories] - Edge case: Fetching from the NY Time Rest API fails
 * @throws [NyTimesEdgeCase.FailedStoringTopStoryLocally] - Edge case: Local storage failed
 *
 * @since v1.0.0
 */
class GetRemoteTopStoriesUseCase(
    private val remoteRepository: ArticleRepository.Remote,
    private val localRepository: ArticleRepository.Local,
) : BaseUseCase<Section, Unit> {
    override suspend fun invoke(input: Section) {
        try {
            remoteRepository.getArticles(input)
        } catch (e: Exception) {
            throw NyTimesEdgeCase.FailedFetchingRemoteTopStories(e)
        }.forEach {
            try {
                localRepository.storeTopStory(it)
            } catch (e: Exception) {
                throw NyTimesEdgeCase.FailedStoringTopStoryLocally(it, e)
            }
        }
    }
}