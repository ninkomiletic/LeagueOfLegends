package com.example.leagueoflegends.agentlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leagueoflegends.base.data.IAgentDataSource
import com.example.leagueoflegends.base.functional.Either
import com.example.leagueoflegends.agentlist.view.AgentListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgentListViewModel(private val dataSource: IAgentDataSource) : ViewModel() {

    private val _state = MutableLiveData<AgentListViewState>()
    val state: LiveData<AgentListViewState>
        get() = _state

    fun getAgents() {
        viewModelScope.launch(Dispatchers.IO) {

            _state.postValue(AgentListViewState.Proccessing)

            _state.postValue(
                when (val result = dataSource.getAllAgents()) {
                    is Either.Success -> AgentListViewState.DataReceived(result.data)
                    is Either.Error -> AgentListViewState.ErrorReceived(result.exception.toString())
                }
            )
        }
    }
}