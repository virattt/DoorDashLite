package com.virat.doordashlite.discover.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.virat.doordashlite.data.models.Restaurant
import com.virat.doordashlite.discover.data.DiscoverRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DiscoverViewModel(
    private val repository: DiscoverRepository
) : ViewModel() {

    val restaurants: MutableLiveData<List<Restaurant>> by lazy {
        MutableLiveData<List<Restaurant>>()
    }

    fun getRestaurants(lat: Double, lng: Double) {
        repository
            .getRestaurants(lat, lng)
            .doOnSubscribe { Log.d(TAG, "Loading") }
            .doOnTerminate { Log.d(TAG, "Finished loading!") }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> restaurants.postValue(result) },
                { error -> Log.e(TAG, "Error occurred when getting restaurants: ${error?.message.orEmpty()}") }
            )
    }

    companion object {
        val TAG = DiscoverViewModel::javaClass.name
    }
}