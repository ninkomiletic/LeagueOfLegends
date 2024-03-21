package com.example.leagueoflegends.agentlist.view

import com.example.leagueoflegends.base.model.Agents

sealed class AgentListViewState {

    object Proccessing : AgentListViewState()
    data class DataReceived(val agents: Agents) : AgentListViewState()
    data class ErrorReceived(val message: String) : AgentListViewState()
}