package com.example.leagueoflegends.agentdetails.view

import com.example.leagueoflegends.base.model.Agents

sealed class AgentDetailsViewState {
    object Proccessing: AgentDetailsViewState()
    data class DataReceived(val agents: Agents) : AgentDetailsViewState()
    data class ErrorReceived(val message: String) : AgentDetailsViewState()
}