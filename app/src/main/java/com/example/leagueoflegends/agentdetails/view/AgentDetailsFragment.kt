package com.example.leagueoflegends.agentdetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBindings
import com.bumptech.glide.Glide
import com.example.leagueoflegends.R
import com.example.leagueoflegends.base.model.Agent
import com.example.leagueoflegends.base.model.Info
import com.example.leagueoflegends.base.model.Stats
import com.example.leagueoflegends.databinding.FragmentAgentDetailsBinding

class AgentDetailsFragment : Fragment() {
    private var _binding: FragmentAgentDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        private var AGENT: Agent = Agent()

        fun newInstance(agent: Agent): AgentDetailsFragment {

            return AgentDetailsFragment().apply {
                arguments = Bundle().apply {
                   AGENT = agent
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAgentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindFromViewModel(AGENT)
    }

    private fun bindFromViewModel(agent: Agent) {
        setUpView(agent)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setUpView(agent: Agent) {
        ViewBindings.findChildViewById<ImageView>(binding.root, R.id.agentImg)?.let {
            Glide.with(this).load("https://www.datocms-assets.com/92583/1675777489-league-of-legends-cover.webp").into(
                it
            )
        }
        ViewBindings.findChildViewById<TextView>(binding.root, R.id.nameTxt)!!.text = agent.name
        ViewBindings.findChildViewById<TextView>(binding.root, R.id.titleTxt)!!.text = agent.title
        ViewBindings.findChildViewById<TextView>(binding.root, R.id.blurbTxt)!!.text = agent.blurb

        ViewBindings.findChildViewById<TextView>(binding.root, R.id.hpValueTxt)!!.text = agent.stats.hp.toString() + " HP"
        ViewBindings.findChildViewById<TextView>(binding.root, R.id.moveSpeedValueTxt)!!.text = agent.stats.movespeed.toString()
        ViewBindings.findChildViewById<TextView>(binding.root, R.id.attackTxt)!!.text = "Attack: " + agent.info.attack.toString()
        ViewBindings.findChildViewById<TextView>(binding.root, R.id.defenceTxt)!!.text = "Defence: " + agent.info.defense.toString()
        ViewBindings.findChildViewById<TextView>(binding.root, R.id.magicTxt)!!.text = "Magic: " + agent.info.magic.toString()
        ViewBindings.findChildViewById<TextView>(binding.root, R.id.difficultyTxt)!!.text = "Difficulty: " + agent.info.difficulty.toString()
    }
}