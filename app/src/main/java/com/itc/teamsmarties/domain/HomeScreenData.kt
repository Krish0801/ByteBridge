package com.itc.teamsmarties.domain

import com.itc.teamsmarties.util.dataMapper.PostData


sealed class HomeScreenData {
    data class Success(
        val listOfPosts: List<PostData>,
    ) : HomeScreenData()
    data class Error(val errorMessage:String):HomeScreenData()
}

