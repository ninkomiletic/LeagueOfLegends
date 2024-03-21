package com.example.leagueoflegends.base.model

data class Agents(
    val type: String = "",
    val format: String = "",
    val version: String = "",
    val data: Array<Agent> = arrayOf()
)

data class Agent(
    val version: String = "",
    val id: String = "",
    val key: String = "",
    val name: String = "",
    val title: String = "",
    val blurb: String = "",
    val info: Info = Info(),
    val image: Image = Image(),
    val tags: List<String> = listOf(),
    val partype: String = "",
    val stats: Stats = Stats(),
)

data class Info(
    val attack: Long = 0,
    val defense: Long = 0,
    val magic: Long = 0,
    val difficulty: Long = 0,
)

data class Image(
    val full: String = "",
    val sprite: String = "",
    val group: String = "",
    val x: Long = 0,
    val y: Long = 0,
    val w: Long = 0,
    val h: Long = 0,
)

data class Stats(
    val hp: Double = 0.0,
    val hpperlevel: Long = 0,
    val mp: Double = 0.0,
    val mpperlevel: Long = 0,
    val movespeed: Long = 0,
    val armor: Double = 0.0,
    val armorperlevel: Double = 0.0,
    val spellblock: Double = 0.0,
    val spellblockperlevel: Double = 0.0,
    val attackrange: Long = 0,
    val hpregen: Double = 0.0,
    val hpregenperlevel: Double = 0.0,
    val mpregen: Double = 0.0,
    val mpregenperlevel: Double = 0.0,
    val crit: Long = 0,
    val critperlevel: Long = 0,
    val attackdamage: Double = 0.0,
    val attackdamageperlevel: Double = 0.0,
    val attackspeedperlevel: Double = 0.0,
    val attackspeed: Double = 0.0,
)
