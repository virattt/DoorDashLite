package com.virat.doordashlite.discover.data

import com.virat.doordashlite.data.api.RestaurantClient.Companion.service
import com.virat.doordashlite.data.models.Restaurant
import io.reactivex.rxjava3.core.Observable

/**
 * A layer that encapsulates the logic for retrieving data models
 * from data sources such as a network API, Room database, etc.
 */
open class DiscoverRepository {

    open fun getRestaurants(lat: Double, lng: Double): Observable<List<Restaurant>> {
        return service.getRestaurants(lat, lng)
    }
}