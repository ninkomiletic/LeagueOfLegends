package com.example.leagueoflegends.agentlist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.leagueoflegends.R
import com.example.leagueoflegends.base.model.Agent
import com.example.leagueoflegends.base.model.Agents

class AgentRVAdapter(private val agents: Agents, private val onItemClicked: (Agent) -> Unit) :
    Adapter<AgentRVViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AgentRVViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_agent, parent, false)
    )

    override fun onBindViewHolder(holder: AgentRVViewHolder, position: Int) {
        holder.bind(agents.data[position], onItemClicked)
    }

    override fun getItemCount() = agents.data.size
}