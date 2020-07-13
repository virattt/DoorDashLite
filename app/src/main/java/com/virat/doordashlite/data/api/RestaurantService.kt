package com.virat.doordashlite.data.api

import com.virat.doordashlite.data.models.Restaurant
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantService {

    @GET("/v2/restaurant")
    fun getRestaurants(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double
    ) : Observable<List<Restaurant>>

    @GET("/v2/restaurant/{id}")
    fun getRestaurant(
        @Path("id") id: Long
    ) : Observable<Restaurant>

    companion object {
        const val BASE_URL = "https://api.doordash.com"
    }
}