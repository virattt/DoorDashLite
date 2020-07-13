package com.virat.doordashlite.detail.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.virat.doordashlite.data.models.Restaurant
import com.virat.doordashlite.detail.data.DetailRepository
import com.virat.doordashlite.discover.data.DiscoverRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailViewModel(
    private val repository: DetailRepository
) : ViewModel() {

    val restaurant: MutableLiveData<Restaurant> by lazy {
        MutableLiveData<Restaurant>()
    }

    fun getRestaurant(id: Long) {
        repository
            .getRestaurant(id)
            .doOnSubscribe { Log.d(TAG, "Loading") }
            .doOnTerminate { Log.d(TAG, "Finished loading!") }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> restaurant.postValue(result) },
                { error -> Log.e(TAG, "Error occurred when getting restaurants: ${error?.message.orEmpty()}") }
            )
    }

    companion object {
        val TAG = DetailViewModel::javaClass.name
    }
}