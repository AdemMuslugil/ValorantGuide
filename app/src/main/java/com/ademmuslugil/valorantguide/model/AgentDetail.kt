package com.ademmuslugil.valorantguide.model

import android.os.Parcelable
import com.ademmuslugil.valorantguide.model.agents.Ability
import kotlinx.parcelize.Parcelize

@Parcelize
data class AgentDetail(
    val agentName: String?,
    val agentType: String?,
    val agentImage: String?,
    val agentDescription: String?,
    val ability: List<Ability>?,
    val background: String?
): Parcelable