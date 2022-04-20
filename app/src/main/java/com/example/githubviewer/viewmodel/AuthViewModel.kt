package com.example.githubviewer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubviewer.repositories.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    var token: MutableLiveData<String> = MutableLiveData("")
    var state: MutableLiveData<State> = MutableLiveData(State.Idle)
    var actions: MutableSharedFlow<Action> = MutableSharedFlow()

    fun onSignButtonPressed(/*token: String*/) {
        signIn(/*token*/)
    }

    sealed interface State {
        object Idle : State
        object Loading : State
        data class InvalidInput(val reason: String) : State
    }

    sealed interface Action {
        data class ShowError(val message: String) : Action
        object RouteToMain : Action
    }

    private fun signIn(/*token: String*/) {
        viewModelScope.launch {
            val res = appRepository.signIn(/*token*/).collect {
                Log.d("EEEEE", Json.decodeFromJsonElement(it))
            }

        }
    }
}