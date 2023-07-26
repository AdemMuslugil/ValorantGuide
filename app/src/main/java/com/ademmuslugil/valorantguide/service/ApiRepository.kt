package com.ademmuslugil.valorantguide.service

import com.ademmuslugil.valorantguide.model.agents.AgentModel
import com.ademmuslugil.valorantguide.model.weapons.WeaponsModel
import io.reactivex.Single
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService)
{
    fun getAgents(): Single<AgentModel> = apiService.getAgents()

    fun getWeapons(): Single<WeaponsModel> = apiService.getWeapons()
}