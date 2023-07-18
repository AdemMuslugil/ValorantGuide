package com.ademmuslugil.valorantguide.service

import com.ademmuslugil.valorantguide.model.agents.AgentModel
import com.ademmuslugil.valorantguide.model.agents.Data
import io.reactivex.Single
import retrofit2.http.GET
import javax.inject.Singleton

interface ApiService {

    @GET("agents")
    fun getAgents(): Single<AgentModel>
}