package com.itc.teamsmarties.data.model.crypto


import com.google.gson.annotations.SerializedName

data class DataModelX(
    @SerializedName("all_awardings")
    val allAwardings: List<AllAwardingModel?>? = listOf(),
    @SerializedName("allow_live_comments")
    val allowLiveComments: Boolean? = false,
    @SerializedName("approved_at_utc")
    val approvedAtUtc: String? = String(),
    @SerializedName("approved_by")
    val approvedBy: String? = String(),
    @SerializedName("archived")
    val archived: Boolean? = false,
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("author_flair_background_color")
    val authorFlairBackgroundColor: String? = "",
    @SerializedName("author_flair_css_class")
    val authorFlairCssClass: String? = "",
    @SerializedName("author_flair_richtext")
    val authorFlairRichtext: List<String?>? = listOf(),
    @SerializedName("author_flair_template_id")
    val authorFlairTemplateId: String? = "",
    @SerializedName("author_flair_text")
    val authorFlairText: String? = "",
    @SerializedName("author_flair_text_color")
    val authorFlairTextColor: String? = "",
    @SerializedName("author_flair_type")
    val authorFlairType: String? = "",
    @SerializedName("author_fullname")
    val authorFullname: String? = "",
    @SerializedName("author_is_blocked")
    val authorIsBlocked: Boolean? = false,
    @SerializedName("author_patreon_flair")
    val authorPatreonFlair: Boolean? = false,
    @SerializedName("author_premium")
    val authorPremium: Boolean? = false,
    @SerializedName("awarders")
    val awarders: List<String?>? = listOf(),
    @SerializedName("banned_at_utc")
    val bannedAtUtc: String? = String(),
    @SerializedName("banned_by")
    val bannedBy: String? = String(),
    @SerializedName("can_gild")
    val canGild: Boolean? = false,
    @SerializedName("can_mod_post")
    val canModPost: Boolean? = false,
    @SerializedName("category")
    val category: String? = String(),
    @SerializedName("clicked")
    val clicked: Boolean? = false,
    @SerializedName("content_categories")
    val contentCategories: String? = String(),
    @SerializedName("contest_mode")
    val contestMode: Boolean? = false,
    @SerializedName("created")
    val created: Double? = 0.0,
    @SerializedName("created_utc")
    val createdUtc: Double? = 0.0,
    @SerializedName("discussion_type")
    val discussionType: String? = String(),
    @SerializedName("distinguished")
    val distinguished: String? = String(),
    @SerializedName("domain")
    val domain: String? = "",
    @SerializedName("downs")
    val downs: Int? = 0,
    @SerializedName("edited")
    val edited: Any? = Any(),
    @SerializedName("gilded")
    val gilded: Int? = 0,
    @SerializedName("gildings")
    val gildings: GildingsModel? = GildingsModel(),
    @SerializedName("hidden")
    val hidden: Boolean? = false,
    @SerializedName("hide_score")
    val hideScore: Boolean? = false,
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("is_created_from_ads_ui")
    val isCreatedFromAdsUi: Boolean? = false,
    @SerializedName("is_crosspostable")
    val isCrosspostable: Boolean? = false,
    @SerializedName("is_meta")
    val isMeta: Boolean? = false,
    @SerializedName("is_original_content")
    val isOriginalContent: Boolean? = false,
    @SerializedName("is_reddit_media_domain")
    val isRedditMediaDomain: Boolean? = false,
    @SerializedName("is_robot_indexable")
    val isRobotIndexable: Boolean? = false,
    @SerializedName("is_self")
    val isSelf: Boolean? = false,
    @SerializedName("is_video")
    val isVideo: Boolean? = false,
    @SerializedName("likes")
    val likes: String? = String(),
    @SerializedName("link_flair_background_color")
    val linkFlairBackgroundColor: String? = "",
    @SerializedName("link_flair_css_class")
    val linkFlairCssClass: String? = "",
    @SerializedName("link_flair_richtext")
    val linkFlairRichtext: List<String?>? = listOf(),
    @SerializedName("link_flair_template_id")
    val linkFlairTemplateId: String? = "",
    @SerializedName("link_flair_text")
    val linkFlairText: String? = "",
    @SerializedName("link_flair_text_color")
    val linkFlairTextColor: String? = "",
    @SerializedName("link_flair_type")
    val linkFlairType: String? = "",
    @SerializedName("locked")
    val locked: Boolean? = false,
    @SerializedName("media")
    val media: String? = String(),
    @SerializedName("media_embed")
    val mediaEmbed: MediaEmbedModel? = MediaEmbedModel(),
    @SerializedName("media_only")
    val mediaOnly: Boolean? = false,
    @SerializedName("mod_note")
    val modNote: String? = String(),
    @SerializedName("mod_reason_by")
    val modReasonBy: String? = String(),
    @SerializedName("mod_reason_title")
    val modReasonTitle: String? = String(),
    @SerializedName("mod_reports")
    val modReports: List<String?>? = listOf(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("no_follow")
    val noFollow: Boolean? = false,
    @SerializedName("num_comments")
    val numComments: Int? = 0,
    @SerializedName("num_crossposts")
    val numCrossposts: Int? = 0,
    @SerializedName("num_reports")
    val numReports: String? = String(),
    @SerializedName("over_18")
    val over18: Boolean? = false,
    @SerializedName("parent_whitelist_status")
    val parentWhitelistStatus: String? = String(),
    @SerializedName("permalink")
    val permalink: String? = "",
    @SerializedName("pinned")
    val pinned: Boolean? = false,
    @SerializedName("pwls")
    val pwls: String? = String(),
    @SerializedName("quarantine")
    val quarantine: Boolean? = false,
    @SerializedName("removal_reason")
    val removalReason: String? = String(),
    @SerializedName("removed_by")
    val removedBy: String? = String(),
    @SerializedName("removed_by_category")
    val removedByCategory: String? = String(),
    @SerializedName("report_reasons")
    val reportReasons: String? = String(),
    @SerializedName("saved")
    val saved: Boolean? = false,
    @SerializedName("score")
    val score: Int? = 0,
    @SerializedName("secure_media")
    val secureMedia: String? = String(),
    @SerializedName("secure_media_embed")
    val secureMediaEmbed: SecureMediaEmbedModel? = SecureMediaEmbedModel(),
    @SerializedName("selftext")
    val selftext: String? = "",
    @SerializedName("selftext_html")
    val selftextHtml: String? = "",
    @SerializedName("send_replies")
    val sendReplies: Boolean? = false,
    @SerializedName("spoiler")
    val spoiler: Boolean? = false,
    @SerializedName("stickied")
    val stickied: Boolean? = false,
    @SerializedName("subreddit")
    val subreddit: String? = "",
    @SerializedName("subreddit_id")
    val subredditId: String? = "",
    @SerializedName("subreddit_name_prefixed")
    val subredditNamePrefixed: String? = "",
    @SerializedName("subreddit_subscribers")
    val subredditSubscribers: Int? = 0,
    @SerializedName("subreddit_type")
    val subredditType: String? = "",
    @SerializedName("suggested_sort")
    val suggestedSort: String? = String(),
    @SerializedName("thumbnail")
    val thumbnail: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("top_awarded_type")
    val topAwardedType: String? = String(),
    @SerializedName("total_awards_received")
    val totalAwardsReceived: Int? = 0,
    @SerializedName("treatment_tags")
    val treatmentTags: List<String?>? = listOf(),
    @SerializedName("ups")
    val ups: Int? = 0,
    @SerializedName("upvote_ratio")
    val upvoteRatio: Double? = 0.0,
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("url_overridden_by_dest")
    val urlOverriddenByDest: String? = "",
    @SerializedName("user_reports")
    val userReports: List<String?>? = listOf(),
    @SerializedName("view_count")
    val viewCount: String? = String(),
    @SerializedName("visited")
    val visited: Boolean? = false,
    @SerializedName("whitelist_status")
    val whitelistStatus: String? = String(),
    @SerializedName("wls")
    val wls: String? = String()
)