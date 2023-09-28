package com.itc.teamsmarties.util.dataMapper

import com.itc.teamsmarties.data.model.crypto.AllAwardingModel
import com.itc.teamsmarties.data.model.sports.LinkFlairRichtextModel
import com.itc.teamsmarties.data.model.technology.PreviewModel


data class PostData(
    val subredditNamePrefixed: String? = null,
    val title: String? = null,
    val thumbnail: String? = null,
    val preview: PreviewModel? = null,
    val sportsPreview: com.itc.teamsmarties.data.model.sports.PreviewModel? = null,
    val author: String? = null,
    val ups: Int? = null,
    val downs: Int? = null,
    val link_flair_text: String? = null,
    val linkFlairRichtext: List<LinkFlairRichtextModel?>? = listOf(),
    val allAwardings: List<AllAwardingModel?>? = listOf(),
    val televisionAllAwardings: List<Any> = listOf(),
    val numComments: Int? = null,
    val created_utc: Double? = null,
    val name: String? = null,
    val body: String? = null,
    val commentsAuthor: String? = null,
    val urlOverriddenByDest: String? = null
)
