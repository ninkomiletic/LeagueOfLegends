package com.example.leagueoflegends.agentlist.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leagueoflegends.R
import com.example.leagueoflegends.base.model.Agent


class AgentRVViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(agent: Agent, onItemClicked: (Agent) -> Unit) {
        Glide.with(itemView).load("https://www.datocms-assets.com/92583/1675777489-league-of-legends-cover.webp")
            .into(itemView.findViewById<ImageView>(R.id.agentCoverImg))
        itemView.findViewById<TextView>(R.id.agentNameTxt).text = agent.name
        itemView.findViewById<TextView>(R.id.titleTxt).text = agent.title

        itemView.setOnClickListener {
            onItemClicked.invoke(agent)
        }
    }
}