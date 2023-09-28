package com.itc.teamsmarties.data.repository

import com.itc.teamsmarties.R
import com.itc.teamsmarties.data.model.food.FoodModel
import com.itc.teamsmarties.data.model.postsById.PostsByIdModel
import com.itc.teamsmarties.data.model.sports.SportsModel
import com.itc.teamsmarties.data.model.technology.TechnologyModel
import com.itc.teamsmarties.data.remote.RedditService
import com.itc.teamsmarties.domain.HomeScreenData
import com.itc.teamsmarties.util.dataMapper.PostData
import com.itc.teamsmarties.util.dataMapper.PostDataMapper
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class RedditRepositoryImpl @Inject constructor(
    private val redditService: RedditService,
    private val postDataMapper: PostDataMapper,
) : RedditRepository {

    override suspend fun getFoodSubRedditData(after: String?): RedditRepository.RepositoryFoodResult {
        return try {
            val response = redditService.getFoodSubRedditData(after)
            response.toResult(postDataMapper)
        } catch (e: Exception) {

            RedditRepository.RepositoryFoodResult.Error(
                (e.localizedMessage ?: R.string.unknown_error) as String
            )

        }
    }

    override suspend fun getSportsSubRedditData(after: String?): RedditRepository.RepositorySportsResult {
        return try {
            val response = redditService.getSportsSubRedditData(after)
            response.toResult(postDataMapper)
        } catch (e: Exception) {
            RedditRepository.RepositorySportsResult.Error(
                (e.localizedMessage ?: R.string.unknown_error) as String
            )
        }
    }

    override suspend fun getTechnologySubRedditData(after: String?): RedditRepository.RepositoryTechnologyResult {
        return try {
            val response = redditService.getTechnologySubRedditData(after)
            response.toResult(postDataMapper)
        } catch (e: Exception) {
            RedditRepository.RepositoryTechnologyResult.Error(
                (e.localizedMessage ?: R.string.unknown_error) as String
            )
        }
    }


    override suspend fun getCombinedData(): HomeScreenData = coroutineScope {
        try {
            val listOfPosts = mutableListOf<PostData>()
            val sportsDeferred = async { getSportsSubRedditData() }
            val technologyDeferred = async { getTechnologySubRedditData() }
            val foodDeferred = async { getFoodSubRedditData() }

            // Await the results of all three async tasks
            val sportsData = sportsDeferred.await()
            if (sportsData is RedditRepository.RepositorySportsResult.Success) {
                listOfPosts.addAll(sportsData.posts)
            }

            val technologyData = technologyDeferred.await()
            if (technologyData is RedditRepository.RepositoryTechnologyResult.Success) {
                listOfPosts.addAll(technologyData.posts)
            }

            val foodData = foodDeferred.await()
            if (foodData is RedditRepository.RepositoryFoodResult.Success) {
                listOfPosts.addAll(foodData.posts)
            }

            if (listOfPosts.isNotEmpty()) {
                HomeScreenData.Success(listOfPosts)
            } else {
                HomeScreenData.Error("No data available")
            }
        } catch (e: Exception) {
            HomeScreenData.Error(e.message.toString())
        }
    }


    override suspend fun getPostsById(postId: String): RedditRepository.RepositoryPostByIdResult {
        return try {
            val response = redditService.getPostsById(postId)
            response.toResult(postDataMapper)
        } catch (e: Exception) {
            RedditRepository.RepositoryPostByIdResult.Error(
                (e.localizedMessage ?: R.string.unknown_error) as String
            )
        }
    }


}

fun PostsByIdModel.toResult(postDataMapper: PostDataMapper): RedditRepository.RepositoryPostByIdResult {
    return RedditRepository.RepositoryPostByIdResult.Success(
        postDataMapper.mapPostsByIdData(this),
        this[0].data.after as String?

    )
}

fun FoodModel.toResult(postDataMapper: PostDataMapper): RedditRepository.RepositoryFoodResult {
    return RedditRepository.RepositoryFoodResult.Success(
        postDataMapper.mapFoodData(this)
    )
}

fun TechnologyModel.toResult(postDataMapper: PostDataMapper): RedditRepository.RepositoryTechnologyResult {
    return RedditRepository.RepositoryTechnologyResult.Success(
        postDataMapper.mapTechnologyData(this)
    )
}

fun SportsModel.toResult(postDataMapper: PostDataMapper): RedditRepository.RepositorySportsResult {
    return RedditRepository.RepositorySportsResult.Success(
        postDataMapper.mapSportsData(this)
    )
}

