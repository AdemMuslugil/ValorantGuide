package com.ademmuslugil.valorantguide.view.weapons

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ademmuslugil.valorantguide.model.weapons.WeaponsModel
import com.ademmuslugil.valorantguide.service.ApiRepository
import com.ademmuslugil.valorantguide.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WeaponsViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {

    private val TAG = "WeaponsViewModel"
    private val disposable = CompositeDisposable()
    val weaponsList = MutableLiveData<WeaponsModel>()
    val isLoading = MutableLiveData<Boolean>()

    fun getWeaponsList() {
        disposable.add(
            apiRepository.getWeapons()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeaponsModel>() {
                    override fun onSuccess(t: WeaponsModel) {
                        weaponsList.value = t
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, e.message, e)
                        isLoading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

