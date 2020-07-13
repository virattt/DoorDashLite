package com.virat.doordashlite.discover.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.virat.doordashlite.discover.data.DiscoverRepository

class DiscoverViewModelFactory(
    private val repository: DiscoverRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DiscoverViewModel(repository) as T
    }
}