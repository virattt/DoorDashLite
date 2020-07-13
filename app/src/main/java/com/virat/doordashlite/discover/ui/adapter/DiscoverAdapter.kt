package com.virat.doordashlite.discover.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.virat.doordashlite.R
import com.virat.doordashlite.data.models.Restaurant
import com.virat.doordashlite.detail.ui.views.DetailActivity
import com.virat.doordashlite.detail.ui.views.DetailActivity.Companion.EXTRA_RESTAURANT_ID
import com.virat.doordashlite.discover.ui.adapter.DiscoverAdapter.RestaurantViewHolder

class DiscoverAdapter : ListAdapter<Restaurant, RestaurantViewHolder>(RestaurantsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.discover_restaurant_row, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.apply {
            setImage(restaurant.coverImageUrl)
            setName(restaurant.name)
            setDescription(restaurant.description)
            setStatus(restaurant.status)
            itemView.setOnClickListener { v ->
                val context = v.context
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra(EXTRA_RESTAURANT_ID, restaurant.id)
                }
                context.startActivity(intent)
            }
        }
    }

    class RestaurantViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private var image: ImageView = view.findViewById(R.id.image)
        private var name: TextView = view.findViewById(R.id.name)
        private var description: TextView = view.findViewById(R.id.description)
        private var status: TextView = view.findViewById(R.id.status)

        fun setImage(url: String?) {
            Glide
                .with(view)
                .load(url)
                .into(image)
        }

        fun setName(name: String?) {
            this.name.text = name
        }

        fun setDescription(description: String?) {
            this.description.text = description
        }

        fun setStatus(status: String?) {
            this.status.text = status
        }
    }
}

class RestaurantsDiffCallback : DiffUtil.ItemCallback<Restaurant>() {

    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem
    }
}