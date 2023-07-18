package com.ademmuslugil.valorantguide.service

import com.ademmuslugil.valorantguide.model.agents.AgentModel
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

class ApiRepository @Inject constructor(private val apiService: ApiService)
{
    fun getAgents(): Single<AgentModel> = apiService.getAgents()
}