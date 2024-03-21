package com.example.leagueoflegends.base.data

import com.example.leagueoflegends.base.functional.Either
import com.example.leagueoflegends.base.model.Agents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

interface IAgentDataSource {
    suspend fun getAllAgents(): Either<Agents>
}

class AgentDataSource(private val apiService: AgentApiService) : IAgentDataSource {
    override suspend fun getAllAgents(): Either<Agents> = handleCall(apiService.getAllAgents())

    private suspend fun <T> handleCall(call: Call<T>): Either<T> {
        return withContext(Dispatchers.IO) {
            val response = call.execute()

            if (response.isSuccessful) {
                Either.Success(response.body()!!)
            } else {
                Either.Error(Exception(response.message()))
            }
        }
    }
}