package com.example.githubviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubviewer.model.KeyValueStorage
import com.example.githubviewer.model.Repo
import com.example.githubviewer.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesListViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val store: KeyValueStorage
) :
    ViewModel() {
    var state: MutableLiveData<State> = MutableLiveData(State.Loading)

    sealed interface State {
        object Loading : State
        data class Loaded(val repos: List<Repo>) : State
        data class Error(val error: String) : State
        object Empty : State
    }

    init {
        fetchRepos()
    }

    private fun fetchRepos() {
        viewModelScope.launch {
            appRepository.getRepositories(store.userName!!).collect {
                state.value = State.Loaded(it)
            }
        }
    }
}