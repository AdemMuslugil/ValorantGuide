package com.ademmuslugil.valorantguide.service

import com.ademmuslugil.valorantguide.model.agents.AgentModel
import com.ademmuslugil.valorantguide.model.weapons.WeaponDetailModel
import com.ademmuslugil.valorantguide.model.weapons.WeaponsModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("agents")
    fun getAgents(
        @Query("language") language:String = "en-US",
        ): Single<AgentModel>

    @GET("weapons")
    fun getWeapons(
        @Query("language") language: String = "en-US"
    ): Single<WeaponsModel>

    @GET("weapons/{weaponUuid}")
    fun getWeaponDetail(
        @Path("weaponUuid") weaponUuid: String = "44d4e95c-4157-0037-81b2-17841bf2e8e3"
        //@Query("language") language: String = "tr-TR"
    ): Single<WeaponDetailModel>


}