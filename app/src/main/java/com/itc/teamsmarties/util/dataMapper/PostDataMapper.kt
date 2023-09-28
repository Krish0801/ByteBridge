package com.itc.teamsmarties.util.dataMapper

import com.itc.teamsmarties.data.model.food.FoodModel
import com.itc.teamsmarties.data.model.postsById.PostsByIdModel
import com.itc.teamsmarties.data.model.sports.SportsModel
import com.itc.teamsmarties.data.model.technology.TechnologyModel


interface PostDataMapper {
    fun mapTechnologyData(technologyResponse: TechnologyModel): List<PostData>
    fun mapSportsData(sportsResponse: SportsModel): List<PostData>
    fun mapFoodData(foodResponse: FoodModel): List<PostData>
    fun mapPostsByIdData(postsByIdResponse: PostsByIdModel): List<PostData>
}