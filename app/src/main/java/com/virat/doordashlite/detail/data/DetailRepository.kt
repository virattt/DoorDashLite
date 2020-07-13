package com.virat.doordashlite.detail.data

import com.virat.doordashlite.data.api.RestaurantClient.Companion.service
import com.virat.doordashlite.data.models.Restaurant
import io.reactivex.rxjava3.core.Observable

/**
 * A layer that encapsulates the logic for retrieving data models
 * from data sources such as a network API, Room database, etc.
 */
open class DetailRepository {

    open fun getRestaurant(id: Long): Observable<Restaurant> {
        return service.getRestaurant(id)
    }
}