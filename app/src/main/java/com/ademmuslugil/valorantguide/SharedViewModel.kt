package com.ademmuslugil.valorantguide

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ademmuslugil.valorantguide.model.agents.AgentDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel() {
    var agent by mutableStateOf<AgentDetail?>(null)
        private set

    fun addAgent(newAgent: AgentDetail){
        agent = newAgent
    }

}