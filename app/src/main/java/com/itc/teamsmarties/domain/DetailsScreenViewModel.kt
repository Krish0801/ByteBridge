package com.itc.teamsmarties.domain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.itc.teamsmarties.data.repository.RedditRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel(
    private val repository: RedditRepository,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<ViewModelState>(
    ViewModelState.Loading,
    savedStateHandle
) {
    @Inject
    constructor(repository: RedditRepository) : this(repository, SavedStateHandle())

    fun getPostsById(postId: String) {

        viewModelScope.launch() {
            loadingState()
            repository.getPostsById(postId).let { result ->
                setState {
                    when (result) {
                        is RedditRepository.RepositoryPostByIdResult.Success -> {

                            ViewModelState.Success(result.posts)
                        }

                        is RedditRepository.RepositoryPostByIdResult.Error -> {
                            ViewModelState.Error(result.exception)
                        }
                    }
                }
            }
        }

    }


}