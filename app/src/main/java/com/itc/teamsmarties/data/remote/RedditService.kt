package com.itc.teamsmarties.data.remote

import com.itc.teamsmarties.data.model.food.FoodModel
import com.itc.teamsmarties.data.model.postsById.PostsByIdModel
import com.itc.teamsmarties.data.model.sports.SportsModel
import com.itc.teamsmarties.data.model.technology.TechnologyModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditService {
    @GET(ApiDetails.SPORTS_ENDPOINT)
    suspend fun getSportsSubRedditData(
        @Query("After") after: String? = null,
    ): SportsModel

    @GET(ApiDetails.Food_ENDPOINT)
    suspend fun getFoodSubRedditData(
        @Query("AFTER") after: String? = null,
    ): FoodModel

    @GET(ApiDetails.TECHNOLOGY_ENDPOINT)
    suspend fun getTechnologySubRedditData(
        @Query("After") after: String? = null,
    ): TechnologyModel

    @GET(ApiDetails.SEARCH_BY_POST_ID_ENDPOINT)
    suspend fun getPostsById(@Path("postId") postId: String): PostsByIdModel
}
