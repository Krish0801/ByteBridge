package com.itc.teamsmarties.domain

import com.itc.teamsmarties.util.dataMapper.PostData
import java.io.Serializable

sealed class ViewModelState : Serializable {
    object Loading : ViewModelState()

    data class Error(val message: String) : ViewModelState()


    data class Success(val data: List<PostData>) : ViewModelState()
}


