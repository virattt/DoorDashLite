package com.virat.doordashlite.discover.ui.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.virat.doordashlite.R
import com.virat.doordashlite.data.models.Restaurant
import com.virat.doordashlite.discover.ui.adapter.DiscoverAdapter
import com.virat.doordashlite.discover.ui.viewmodel.DiscoverViewModel
import com.virat.doordashlite.utils.DependencyInjector.createDiscoverViewModelFactory

class DiscoverActivity : AppCompatActivity() {
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView
    @BindView(R.id.progress_bar) lateinit var progressBar: ProgressBar

    private val viewModel: DiscoverViewModel by viewModels { createDiscoverViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)
        ButterKnife.bind(this)

        val adapter = DiscoverAdapter()
        val linearLayoutManager = LinearLayoutManager(this)

        setUpRecyclerView(adapter, linearLayoutManager)
        observeLiveData(adapter)

        viewModel.getRestaurants(DOORDASH_LAT, DOORDASH_LNG)
    }

    private fun setUpRecyclerView(
        discoverAdapter: DiscoverAdapter,
        linearLayoutManager: LinearLayoutManager
    ) {
        recyclerView.apply {
            adapter = discoverAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        }
    }

    private fun observeLiveData(discoverAdapter: DiscoverAdapter) {
        viewModel.restaurants.observe(this,
            Observer<List<Restaurant>> { restaurants ->
                restaurants?.let {
                    progressBar.visibility = View.GONE
                    discoverAdapter.submitList(restaurants)
                }
            }
        )
    }

    companion object  {
        private const val DOORDASH_LAT = 37.422740
        private const val DOORDASH_LNG = -122.139956
    }
}