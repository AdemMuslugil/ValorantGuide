package com.ademmuslugil.valorantguide.view.weapons.weapondetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ademmuslugil.valorantguide.model.weapons.WeaponDetailModel
import com.ademmuslugil.valorantguide.service.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WeaponDetailViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {

    private val TAG = "WeaponDetailViewModel"
    private val disposable = CompositeDisposable()
    val weaponData = MutableLiveData<WeaponDetailModel>()
    val isLoading = MutableLiveData<Boolean>()

    fun getWeaponDetail(id: String){
        disposable.add(
            apiRepository.getWeaponDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeaponDetailModel>(){
                    override fun onSuccess(t: WeaponDetailModel) {
                        weaponData.value = t
                    }

                    override fun onError(e: Throwable) {
                         Log.e(TAG,e.message,e)
                        isLoading.value = false
                    }

                })
        )
    }
}