package com.example.leagueoflegends.base.functional

import kotlin.coroutines.CoroutineContext

interface ICoroutineContextProvider {
    val main: CoroutineContext
    val io: CoroutineContext
    val default: CoroutineContext
}