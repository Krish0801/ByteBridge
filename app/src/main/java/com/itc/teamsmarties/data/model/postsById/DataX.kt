package com.itc.teamsmarties.data.model.postsById


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("all_awardings")
    val allAwardings: List<AllAwarding>? = null,
    @SerializedName("allow_live_comments")
    val allowLiveComments: Boolean? = null,
    @SerializedName("approved_at_utc")
    val approvedAtUtc: Any? = null,
    @SerializedName("approved_by")
    val approvedBy: Any? = null,
    @SerializedName("archived")
    val archived: Boolean? = null,
    @SerializedName("associated_award")
    val associatedAward: Any? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("author_flair_background_color")
    val authorFlairBackgroundColor: String? = null,
    @SerializedName("author_flair_css_class")
    val authorFlairCssClass: String? = null,
    @SerializedName("author_flair_richtext")
    val authorFlairRichtext: List<Any>? = null,
    @SerializedName("author_flair_template_id")
    val authorFlairTemplateId: String? = null,
    @SerializedName("author_flair_text")
    val authorFlairText: String? = null,
    @SerializedName("author_flair_text_color")
    val authorFlairTextColor: String? = null,
    @SerializedName("author_flair_type")
    val authorFlairType: String? = null,
    @SerializedName("author_fullname")
    val authorFullname: String? = null,
    @SerializedName("author_is_blocked")
    val authorIsBlocked: Boolean? = null,
    @SerializedName("author_patreon_flair")
    val authorPatreonFlair: Boolean? = null,
    @SerializedName("author_premium")
    val authorPremium: Boolean? = null,
    @SerializedName("awarders")
    val awarders: List<Any>? = null,
    @SerializedName("banned_at_utc")
    val bannedAtUtc: Any? = null,
    @SerializedName("banned_by")
    val bannedBy: Any? = null,
    @SerializedName("body")
    val body: String? = null,
    @SerializedName("body_html")
    val bodyHtml: String? = null,
    @SerializedName("can_gild")
    val canGild: Boolean? = null,
    @SerializedName("can_mod_post")
    val canModPost: Boolean? = null,
    @SerializedName("category")
    val category: Any? = null,
    @SerializedName("children")
    val children: List<String>? = null,
    @SerializedName("clicked")
    val clicked: Boolean? = null,
    @SerializedName("collapsed")
    val collapsed: Boolean? = null,
    @SerializedName("collapsed_because_crowd_control")
    val collapsedBecauseCrowdControl: Any? = null,
    @SerializedName("collapsed_reason")
    val collapsedReason: String? = null,
    @SerializedName("collapsed_reason_code")
    val collapsedReasonCode: String? = null,
    @SerializedName("comment_type")
    val commentType: Any? = null,
    @SerializedName("content_categories")
    val contentCategories: Any? = null,
    @SerializedName("contest_mode")
    val contestMode: Boolean? = null,
    @SerializedName("controversiality")
    val controversiality: Int? = null,
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("created")
    val created: Double? = null,
    @SerializedName("created_utc")
    val createdUtc: Double? = null,
    @SerializedName("depth")
    val depth: Int? = null,
    @SerializedName("discussion_type")
    val discussionType: Any? = null,
    @SerializedName("distinguished")
    val distinguished: String? = null,
    @SerializedName("domain")
    val domain: String? = null,
    @SerializedName("downs")
    val downs: Int? = null,
    @SerializedName("edited")
    val edited: Any? = null,
    @SerializedName("gilded")
    val gilded: Int? = null,
    @SerializedName("gildings")
    val gildings: Gildings? = null,
    @SerializedName("hidden")
    val hidden: Boolean? = null,
    @SerializedName("hide_score")
    val hideScore: Boolean? = null,
    @SerializedName("id")
    val id: String?=null,
    @SerializedName("is_created_from_ads_ui")
    val isCreatedFromAdsUi: Boolean? = null,
    @SerializedName("is_crosspostable")
    val isCrosspostable: Boolean? = null,
    @SerializedName("is_meta")
    val isMeta: Boolean? = null,
    @SerializedName("is_original_content")
    val isOriginalContent: Boolean? = null,
    @SerializedName("is_reddit_media_domain")
    val isRedditMediaDomain: Boolean? = null,
    @SerializedName("is_robot_indexable")
    val isRobotIndexable: Boolean? = null,
    @SerializedName("is_self")
    val isSelf: Boolean? = null,
    @SerializedName("is_submitter")
    val isSubmitter: Boolean? = null,
    @SerializedName("is_video")
    val isVideo: Boolean? = null,
    @SerializedName("likes")
    val likes: Any? = null,
    @SerializedName("link_flair_background_color")
    val linkFlairBackgroundColor: String? = null,
    @SerializedName("link_flair_css_class")
    val linkFlairCssClass: String? = null,
    @SerializedName("link_flair_richtext")
    val linkFlairRichtext: List<Any>? = null,
    @SerializedName("link_flair_template_id")
    val linkFlairTemplateId: String? = null,
    @SerializedName("link_flair_text")
    val linkFlairText: String? = null,
    @SerializedName("link_flair_text_color")
    val linkFlairTextColor: String? = null,
    @SerializedName("link_flair_type")
    val linkFlairType: String? = null,
    @SerializedName("link_id")
    val linkId: String? = null,
    @SerializedName("locked")
    val locked: Boolean? = null,
    @SerializedName("media")
    val media: Any? = null,
    @SerializedName("media_embed")
    val mediaEmbed: MediaEmbed? = null,
    @SerializedName("media_only")
    val mediaOnly: Boolean? = null,
    @SerializedName("mod_note")
    val modNote: Any? = null,
    @SerializedName("mod_reason_by")
    val modReasonBy: Any? = null,
    @SerializedName("mod_reason_title")
    val modReasonTitle: Any? = null,
    @SerializedName("mod_reports")
    val modReports: List<Any>? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("no_follow")
    val noFollow: Boolean? = null,
    @SerializedName("num_comments")
    val numComments: Int? = null,
    @SerializedName("num_crossposts")
    val numCrossposts: Int? = null,
    @SerializedName("num_duplicates")
    val numDuplicates: Int? = null,
    @SerializedName("num_reports")
    val numReports: Any? = null,
    @SerializedName("over_18")
    val over18: Boolean? = null,
    @SerializedName("parent_id")
    val parentId: String? = null,
    @SerializedName("parent_whitelist_status")
    val parentWhitelistStatus: Any? = null,
    @SerializedName("permalink")
    val permalink: String? = null,
    @SerializedName("pinned")
    val pinned: Boolean? = null,
    @SerializedName("pwls")
    val pwls: Any? = null,
    @SerializedName("quarantine")
    val quarantine: Boolean? = null,
    @SerializedName("removal_reason")
    val removalReason: Any? = null,
    @SerializedName("removed_by")
    val removedBy: Any? = null,
    @SerializedName("removed_by_category")
    val removedByCategory: Any? = null,
    @SerializedName("replies")
    val replies: Any? = null,
    @SerializedName("report_reasons")
    val reportReasons: Any? = null,
    @SerializedName("saved")
    val saved: Boolean? = null,
    @SerializedName("score")
    val score: Int? = null,
    @SerializedName("score_hidden")
    val scoreHidden: Boolean? = null,
    @SerializedName("secure_media")
    val secureMedia: Any? = null,
    @SerializedName("secure_media_embed")
    val secureMediaEmbed: SecureMediaEmbed? = null,
    @SerializedName("selftext")
    val selftext: String? = null,
    @SerializedName("selftext_html")
    val selftextHtml: Any? = null,
    @SerializedName("send_replies")
    val sendReplies: Boolean? = null,
    @SerializedName("spoiler")
    val spoiler: Boolean? = null,
    @SerializedName("stickied")
    val stickied: Boolean? = null,
    @SerializedName("subreddit")
    val subreddit: String? = null,
    @SerializedName("subreddit_id")
    val subredditId: String? = null,
    @SerializedName("subreddit_name_prefixed")
    val subredditNamePrefixed: String? = null,
    @SerializedName("subreddit_subscribers")
    val subredditSubscribers: Int? = null,
    @SerializedName("subreddit_type")
    val subredditType: String? = null,
    @SerializedName("suggested_sort")
    val suggestedSort: Any? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("top_awarded_type")
    val topAwardedType: Any? = null,
    @SerializedName("total_awards_received")
    val totalAwardsReceived: Int? = null,
    @SerializedName("treatment_tags")
    val treatmentTags: List<Any>? = null,
    @SerializedName("unrepliable_reason")
    val unrepliableReason: Any? = null,
    @SerializedName("ups")
    val ups: Int? = null,
    @SerializedName("upvote_ratio")
    val upvoteRatio: Double? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("url_overridden_by_dest")
    val urlOverriddenByDest: String? = null,
    @SerializedName("user_reports")
    val userReports: List<Any>? = null,
    @SerializedName("view_count")
    val viewCount: Any? = null,
    @SerializedName("visited")
    val visited: Boolean? = null,
    @SerializedName("whitelist_status")
    val whitelistStatus: Any? = null,
    @SerializedName("wls")
    val wls: Any? = null
)

