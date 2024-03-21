package com.example.leagueoflegends.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leagueoflegends.R
import com.example.leagueoflegends.base.model.Agent
import com.example.leagueoflegends.agentlist.view.AgentListFragment
import com.example.leagueoflegends.agentdetails.view.AgentDetailsFragment

class MainActivity : AppCompatActivity(), ICoordinator {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        showFragment(AgentListFragment(), true)
    }

    private fun showFragment(fragment: Fragment, addAsRoot: Boolean = false) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        if (!addAsRoot) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showDetailsFragment(agent: Agent) {
        showFragment(AgentDetailsFragment.newInstance(agent))
    }

}