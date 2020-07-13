package com.virat.doordashlite.detail.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.virat.doordashlite.detail.data.DetailRepository

class DetailViewModelFactory(
    private val repository: DetailRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(repository) as T
    }
}