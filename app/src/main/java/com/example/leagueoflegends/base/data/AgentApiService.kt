package com.example.leagueoflegends.base.data

import com.example.leagueoflegends.base.model.Agents
import retrofit2.Call
import retrofit2.http.GET

interface AgentApiService {

    @GET("70340ba3-4950-4179-811e-c90feb2bd063/agents")
    fun getAllAgents(): Call<Agents>
}