package me.hectorhalpizar.core.nytimes.data

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import me.hectorhalpizar.core.nytimes.domain.Article
import me.hectorhalpizar.core.nytimes.domain.Section

interface ArticleRepository {
    /**
     * Handles Articles from a REST API
     */
    interface Remote {
        /**
         * Get the Top Stories articles from the REST API
         *
         * @param section   Section of the topStories List
         * @return [List] of [Article]
         */
        suspend fun getArticles(section: Section): List<Article>
    }
    /**
     * Handles favorite articles repository
     */
    interface Local {
        /**
         * Adds a top story [Article] to the device local storage
         *
         * @param article Top story [Article] to add to the device
         */
        suspend fun storeTopStory(article: Article)
        /**
         * Get the Top Stories articles from the device local storage
         *
         * @param section   Section of the topStories List
         * @return [List] of [Article]
         */
        suspend fun getArticles(section: Section): List<Article>
        /**
         * Add a favorite article to the device local storage
         *
         * @param article   Favorite [Article] to save
         */
        suspend fun addFavorite(article: Article)
        /**
         * Remove favorite article to the device local storage
         *
         * @param article   Favorite [Article] to remove
         */
        suspend fun removeFavorite(article: Article)
        /**
         * Get favorite articles from the device local storage
         *
         * @return The [List] of [Article] stored in the device
         */
        suspend fun getFavorite() : List<Article>
        /**
         * Get the latest date when the articles were got remotely
         *
         * @param section   [Section] to get the latest date
         * @return  Latest date when the user got a particular [List] of [Article] [Section]
         */
        suspend fun getLatestRemoteFetchDate(section: Section): Instant
        /**
         *
         * @param section   [Section] to set the latest date
         * @param date      A date to set
         * @see [Remote.getArticles]
         */
        suspend fun setLatestRemoteFetchDate(section: Section, date: LocalDate)
    }
}