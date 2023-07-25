package com.ademmuslugil.valorantguide.service

import com.ademmuslugil.valorantguide.model.agents.AgentModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("agents")
    fun getAgents(
        @Query("language") language:String = "en-US",
        ): Single<AgentModel>
}