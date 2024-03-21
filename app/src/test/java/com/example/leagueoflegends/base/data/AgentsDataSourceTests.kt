package com.example.leagueoflegends.base.data

import com.example.leagueoflegends.base.functional.Either
import com.example.leagueoflegends.base.model.Agents
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import retrofit2.Call
import retrofit2.Response

class AgentsDataSourceTests {

    @Mock
    lateinit var apiService: AgentApiService

    @Mock
    lateinit var getAgentsCall: Call<Agents>

    lateinit var dataSource: AgentDataSource

    @Before
    fun setUp() {
        openMocks(this)
        dataSource = AgentDataSource(apiService)
    }

    @Test
    fun `testGetAgents, has response, Success returned`() = runBlocking {
        val expectedAgents: Agents = Agents()
        val expectedResult = Either.Success(expectedAgents)

        `when`(apiService.getAllAgents()).thenReturn(getAgentsCall)
        `when`(getAgentsCall.execute()).thenReturn(Response.success(expectedAgents))

        val result = dataSource.getAllAgents()

        assertEquals(expectedResult, result)
    }

    @Test
    fun `testGetAgents, has error, Error returned`() = runBlocking {
        val expectedResponseBody = ResponseBody.create(
            MediaType.parse("application/json"), ""
        )

        `when`(apiService.getAllAgents()).thenReturn(getAgentsCall)
        `when`(getAgentsCall.execute()).thenReturn(Response.error(400, expectedResponseBody))


        val result = dataSource.getAllAgents()

        assertTrue(result is Either.Error)
    }
}