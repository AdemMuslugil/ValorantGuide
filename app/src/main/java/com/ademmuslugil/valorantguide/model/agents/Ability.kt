package com.ademmuslugil.valorantguide.model.agents

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ability(
    val description: String?,
    val displayIcon: String?,
    val displayName: String?,
    val slot: String?
): Parcelable