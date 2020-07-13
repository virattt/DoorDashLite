package com.virat.doordashlite.detail.ui.views

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.virat.doordashlite.R
import com.virat.doordashlite.data.models.Restaurant
import com.virat.doordashlite.detail.ui.viewmodel.DetailViewModel
import com.virat.doordashlite.utils.DependencyInjector

class DetailActivity : AppCompatActivity() {
    @BindView(R.id.image) lateinit var image: ImageView
    @BindView(R.id.name) lateinit var name: TextView
    @BindView(R.id.description) lateinit var description: TextView
    @BindView(R.id.status) lateinit var status: TextView
    @BindView(R.id.delivery_fee) lateinit var deliveryFee: TextView
    @BindView(R.id.progress_bar) lateinit var progressBar: ProgressBar

    private val viewModel: DetailViewModel by viewModels { DependencyInjector.createDetailViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        ButterKnife.bind(this)

        val id = intent.getLongExtra(EXTRA_RESTAURANT_ID, 0)

        viewModel.getRestaurant(id)
        viewModel.restaurant.observe(this,
            Observer<Restaurant> { restaurant ->
                restaurant?.let {
                    progressBar.visibility  = View.GONE
                    Glide.with(this@DetailActivity).load(it.coverImageUrl).into(image)
                    name.text = it.name
                    description.text = it.description
                    status.text = it.status
                    deliveryFee.text = it.getFormattedDeliveryFee()
                }
            })
    }

    companion object {
        const val EXTRA_RESTAURANT_ID = "EXTRA_RESTAURANT_ID"
    }
}