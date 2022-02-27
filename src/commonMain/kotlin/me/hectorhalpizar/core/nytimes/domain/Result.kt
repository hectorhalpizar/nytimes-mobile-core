package me.hectorhalpizar.core.nytimes.domain

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class Result(
     val status: String,
     val copyright: String,
     val section: String,
     val last_updated: Instant,
     val num_results: Int,
     val results: List<Article>
)