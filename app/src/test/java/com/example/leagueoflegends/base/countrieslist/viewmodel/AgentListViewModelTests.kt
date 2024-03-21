package com.example.leagueoflegends.base.countrieslist.viewmodel

import androidx.lifecycle.Observer
import com.example.leagueoflegends.base.InstantExecutorTest
import com.example.leagueoflegends.base.data.IAgentDataSource
import com.example.leagueoflegends.base.functional.Either
import com.example.leagueoflegends.base.model.Agents
import com.example.leagueoflegends.agentlist.view.AgentListViewState
import com.example.leagueoflegends.agentlist.view.AgentListViewState.*
import com.example.leagueoflegends.agentlist.viewmodel.AgentListViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.openMocks

class AgentListViewModelTests : InstantExecutorTest() {
    @Mock
    lateinit var dataSource: IAgentDataSource

    @Mock
    lateinit var stateObserver: Observer<AgentListViewState>

    lateinit var viewModel: AgentListViewModel

    @Before
    fun setUp() {
        openMocks(this)
        viewModel = AgentListViewModel(dataSource)
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `testGetAgents, has result, state changed to Proccessing - DataReceived`() = runBlocking {
        val expectedResult: Agents = Agents()

        `when`(dataSource.getAllAgents()).thenReturn(Either.Success(expectedResult))

        viewModel.getAgents()

        verify(stateObserver).onChanged(Proccessing)
        verify(stateObserver).onChanged(DataReceived(expectedResult))
    }

    @Test
    fun `testGetAgents, has error, state changed to Proccessing - ErrorReceived`() = runBlocking {
        val expectedError = Exception("test")

        `when`(dataSource.getAllAgents()).thenReturn(Either.Error(expectedError))

        viewModel.getAgents()

        verify(stateObserver).onChanged(ErrorReceived(expectedError.toString()))
    }
}