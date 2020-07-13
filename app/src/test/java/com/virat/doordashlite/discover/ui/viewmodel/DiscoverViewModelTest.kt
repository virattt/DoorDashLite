package com.virat.doordashlite.discover.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.virat.doordashlite.data.models.Restaurant
import com.virat.doordashlite.discover.data.DiscoverRepository
import com.virat.doordashlite.rx.RxSchedulerRule
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`


class DiscoverViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxSchedulerRule = RxSchedulerRule()

    lateinit var mockRepository: DiscoverRepository

    @Before
    fun setup() {
        mockRepository = Mockito.mock(DiscoverRepository::class.java)
    }

    @Test
    fun `Given a successful getRestaurants() API call, restaurants LiveData should be non-null`() {
        // GIVEN
        val viewModel = DiscoverViewModel(mockRepository)
        val restaurant = Restaurant(id = 1)

        // WHEN
        `when`(mockRepository.getRestaurants(DEFAULT_LAT, DEFAULT_LNG)).thenReturn(Observable.just(listOf(restaurant)))
        viewModel.getRestaurants(DEFAULT_LAT, DEFAULT_LNG) // execute "API call"

        // THEN
        Truth.assert_()
            .that(viewModel.restaurants.value)
            .isEqualTo(listOf(restaurant))
    }

    @Test
    fun `Given a failed getRestaurants() API call, restaurants LiveData should be null`() {
        // GIVEN
        val viewModel = DiscoverViewModel(mockRepository)

        // WHEN
        `when`(mockRepository.getRestaurants(DEFAULT_LAT, DEFAULT_LNG)).thenReturn(Observable.error(Exception()))
        viewModel.getRestaurants(DEFAULT_LAT, DEFAULT_LNG) // execute "API call"

        // THEN
        Truth.assert_()
            .that(viewModel.restaurants.value)
            .isEqualTo(null)
    }

    companion object {
        const val DEFAULT_LAT = 0.0
        const val DEFAULT_LNG = 0.0
    }
}