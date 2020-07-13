package com.virat.doordashlite.utils

import com.virat.doordashlite.detail.data.DetailRepository
import com.virat.doordashlite.detail.ui.viewmodel.DetailViewModelFactory
import com.virat.doordashlite.discover.data.DiscoverRepository
import com.virat.doordashlite.discover.ui.viewmodel.DiscoverViewModelFactory

/**
 * Injects app-level dependencies.
 * For a production app, we would replace this with Dagger.
 */
object DependencyInjector {

    fun createDiscoverViewModelFactory(): DiscoverViewModelFactory {
        return DiscoverViewModelFactory(DiscoverRepository())
    }

    fun createDetailViewModelFactory(): DetailViewModelFactory {
        return DetailViewModelFactory(DetailRepository())
    }
}