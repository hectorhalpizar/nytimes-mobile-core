package me.hectorhalpizar.core.nytimes.usecase

import me.hectorhalpizar.core.nytimes.data.ArticleRepository
import me.hectorhalpizar.core.nytimes.domain.Article
import me.hectorhalpizar.core.nytimes.domain.Section

/**
 * Retrieve a [Map] of [Article] from the device
 *
 * @param localRepository - [ArticleRepository.Local] will fetch the [List] of [Article] from the
 *
 * @throws [NyTimesEdgeCase.FailedFetchingLocalTopStories] - Edge case: Failed fetching the articles from the device.
 * @throws [NyTimesEdgeCase.NoArticlesStoredLocallyAvailable] - Edge case: There are no articles stored in the device.
 *
 * @since v1.0.0
 */
class GetLocalTopStoriesUseCase(
    private val localRepository: ArticleRepository.Local
): BaseUseCase<Section, Map<String, Article>> {
    override suspend fun invoke(input: Section): Map<String, Article> {
        try {
            localRepository.getArticles(input)
        } catch (e: Exception) {
            throw NyTimesEdgeCase.FailedFetchingLocalTopStories(e)
        }.also { articles ->
            if (articles.isEmpty()) {
                throw NyTimesEdgeCase.NoArticlesStoredLocallyAvailable()
            } else {
                val result = hashMapOf<String, Article>()
                articles.forEach { article ->
                    result[article.short_url] = article
                }
                return result
            }
        }
    }
}