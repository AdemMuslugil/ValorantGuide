package com.ademmuslugil.valorantguide.view.agents

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ademmuslugil.valorantguide.model.agents.AgentModel
import com.ademmuslugil.valorantguide.service.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {

    private val TAG = "AgentsViewModel"
    private val disposable = CompositeDisposable()
    var agentsList = MutableLiveData<AgentModel>()
    var isLoading = MutableLiveData<Boolean>()

    fun getAgentsFromApi(context: Context) {
            disposable.add(
                apiRepository.getAgents()
                    .subscribeOn(Schedulers.newThread()) //allows us to do the operation in a different thread
                    .observeOn(AndroidSchedulers.mainThread()) //select the thread we want to observe the operation
                    .subscribeWith(object : DisposableSingleObserver<AgentModel>() {
                        override fun onSuccess(t: AgentModel) {
                            agentsList.value = t
                        }

                        override fun onError(e: Throwable) {
                            isLoading.value = false
                            Log.e(TAG, e.message, e)
                            Toast.makeText(context, "Error fetch dat", Toast.LENGTH_SHORT).show()
                        }
                    })
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}