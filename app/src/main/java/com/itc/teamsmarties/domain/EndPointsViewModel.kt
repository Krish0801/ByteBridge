package com.itc.teamsmarties.domain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.itc.teamsmarties.data.repository.RedditRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EndPointsViewModel(
    private val repository: RedditRepository,
    savedStateHandle: SavedStateHandle,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
) : BaseViewModel<ViewModelState>
    (
    ViewModelState.Loading,
    savedStateHandle

) {
    @Inject
    constructor(
        repository: RedditRepository,
    ) : this(repository, SavedStateHandle())

    private var after = ""


    fun getFoodSubRedditData() {

        viewModelScope.launch(dispatcher) {
            loadingState()
            repository.getFoodSubRedditData().let { result ->
                setState {
                    when (result) {
                        is RedditRepository.RepositoryFoodResult.Success -> {
                            //after = result.after.toString()
                            ViewModelState.Success(result.posts)
                        }

                        is RedditRepository.RepositoryFoodResult.Error -> {
                            ViewModelState.Error(result.exception)
                        }
                    }
                }
            }
        }

    }

    fun getSportsSubRedditData() {

        viewModelScope.launch(dispatcher) {
            loadingState()
            repository.getSportsSubRedditData().let { result ->
                setState {
                    when (result) {
                        is RedditRepository.RepositorySportsResult.Success -> {
                            //  after = result.after.toString()
                            ViewModelState.Success(result.posts)
                        }

                        is RedditRepository.RepositorySportsResult.Error -> {
                            ViewModelState.Error(result.exception)
                        }
                    }
                }
            }
        }


    }

    fun getTechnologySubRedditData() {

        viewModelScope.launch(dispatcher) {
            loadingState()
            repository.getTechnologySubRedditData().let { result ->
                setState {
                    when (result) {
                        is RedditRepository.RepositoryTechnologyResult.Success -> {
                            // after = result.after.toString()
                            ViewModelState.Success(result.posts)
                        }

                        is RedditRepository.RepositoryTechnologyResult.Error -> {
                            ViewModelState.Error(result.exception)
                        }
                    }
                }
            }
        }


    }

    fun getHomeScreenData() {
        viewModelScope.launch(dispatcher) {
            loadingState()
            repository.getCombinedData().let { result ->

                setState {
                    when (result) {
                        is HomeScreenData.Success -> {
                            ViewModelState.Success(result.listOfPosts)

                        }

                        is HomeScreenData.Error -> {
                            ViewModelState.Error(result.errorMessage)
                        }
                    }
                }
            }


        }
    }
}