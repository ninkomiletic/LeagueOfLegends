package com.example.leagueoflegends.base.data

import com.example.leagueoflegends.base.data.AgentApiService

object ApiServiceProvider {
    val agentsApiService = RetrofitBuilder.retrofit.create(AgentApiService::class.java)
}