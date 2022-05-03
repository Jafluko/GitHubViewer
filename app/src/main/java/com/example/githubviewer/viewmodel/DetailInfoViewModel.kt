package com.example.githubviewer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubviewer.model.KeyValueStorage
import com.example.githubviewer.model.Repo
import com.example.githubviewer.model.RepoDetails
import com.example.githubviewer.model.Response
import com.example.githubviewer.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailInfoViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val store: KeyValueStorage
) : ViewModel() {
    var state: MutableLiveData<State> = MutableLiveData(State.Loading)

    fun setInfo(repoId: Int, repoName: String) {
        viewModelScope.launch {
            val res = appRepository.getRepository(repoName, store.userName!!).collect { repo ->
                when(repo) {
                    is Response.Success -> {
                        state.value = State.Loaded(repo.data!!, ReadmeState.Loading)
                        val res = appRepository.getRepositoryReadme(store.userName!!, repoName, repo.data.branch!!).collect { readMe ->
                            when(readMe) {
                                is Response.Success -> {
                                    Log.d("rrrr", readMe.data!!)
                                    state.value = State.Loaded(repo.data, ReadmeState.Loaded(readMe.data))
                                }
                                is Response.Error -> {

                                }
                            }
                        }
                    }
                    is Response.Error -> {
                        Log.d("Error", repo.message!!)
                    }
                }
            }
        }
    }

    sealed interface State {
        object Loading : State
        data class Error(val error: String) : State

        data class Loaded(
            val githubRepo: RepoDetails,
            val readmeState: ReadmeState
        ) : State
    }

    sealed interface ReadmeState {
        object Loading : ReadmeState
        object Empty : ReadmeState
        data class Error(val error: String) : ReadmeState
        data class Loaded(val markdown: String) : ReadmeState
    }
}