package com.example.leagueoflegends.agentlist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.leagueoflegends.base.ICoordinator
import com.example.leagueoflegends.base.data.ApiServiceProvider
import com.example.leagueoflegends.base.data.AgentDataSource
import com.example.leagueoflegends.base.functional.ViewModelFactoryUtil
import com.example.leagueoflegends.base.model.Agents
import com.example.leagueoflegends.agentlist.recycler.AgentRVAdapter
import com.example.leagueoflegends.agentlist.viewmodel.AgentListViewModel
import com.example.leagueoflegends.databinding.FragmentAgentListBinding

class AgentListFragment : Fragment() {

    lateinit var viewModel: AgentListViewModel
    private var _binding: FragmentAgentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            AgentListViewModel(AgentDataSource(ApiServiceProvider.agentsApiService))
        }).get(AgentListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAgentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindFromViewModel()
        viewModel.getAgents()
    }

    private fun setUpRecyclerView(agents: Agents) {
        _binding?.agentListRV?.adapter = AgentRVAdapter(agents) { agent ->
            (activity as ICoordinator).showDetailsFragment(agent)
        }
    }

    private fun bindFromViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->

            _binding?.agentListProgressBar?.isVisible = state is AgentListViewState.Proccessing

            when (state) {
                is AgentListViewState.DataReceived -> { setUpRecyclerView(state.agents) }
                is AgentListViewState.ErrorReceived -> showError(state.message)
                else -> {}
            }

        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}