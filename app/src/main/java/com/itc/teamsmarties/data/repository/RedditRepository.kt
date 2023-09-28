package com.itc.teamsmarties.data.repository


import com.itc.teamsmarties.domain.HomeScreenData
import com.itc.teamsmarties.util.dataMapper.PostData

interface RedditRepository {

    suspend fun getSportsSubRedditData(after: String? = null): RepositorySportsResult
    suspend fun getTechnologySubRedditData(after: String? = null): RepositoryTechnologyResult

    suspend fun getFoodSubRedditData(after: String? = null): RepositoryFoodResult

    suspend fun getCombinedData(): HomeScreenData

    suspend fun getPostsById(postId: String): RepositoryPostByIdResult

    sealed class RepositoryPostByIdResult {
        data class Success(val posts: List<PostData>, val after: String?) :
            RepositoryPostByIdResult()

        data class Error(val exception: String) : RepositoryPostByIdResult()

    }

    sealed class RepositoryFoodResult {
        data class Success(val posts: List<PostData>) :
            RepositoryFoodResult()

        data class Error(val exception: String) : RepositoryFoodResult()

    }

    sealed class RepositorySportsResult {
        data class Success(val posts: List<PostData>) :
            RepositorySportsResult()

        data class Error(val exception: String) : RepositorySportsResult()

    }

    sealed class RepositoryTechnologyResult {
        data class Success(val posts: List<PostData>) :
            RepositoryTechnologyResult()

        data class Error(val exception: String) : RepositoryTechnologyResult()

    }


}
