package com.virat.doordashlite.data.api

import com.virat.doordashlite.data.api.RestaurantService.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RestaurantClient {

    // Can improve this companion object and use Dagger to inject a
    // RestaurantService instance that is annotated with @Singleton
    companion object {
        val service: RestaurantService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(RestaurantService::class.java)
        }
    }
}