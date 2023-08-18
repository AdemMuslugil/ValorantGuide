package com.ademmuslugil.valorantguide.model.agents

import android.os.Parcelable
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