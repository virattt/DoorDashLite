package com.virat.doordashlite.detail.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.virat.doordashlite.data.models.Restaurant
import com.virat.doordashlite.detail.data.DetailRepository
import com.virat.doordashlite.rx.RxSchedulerRule
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class DetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rxSchedulerRule = RxSchedulerRule()

    lateinit var mockRepository: DetailRepository

    @Before
    fun setup() {
        mockRepository = Mockito.mock(DetailRepository::class.java)
    }

    @Test
    fun `Given a successful getRestaurant() API call, restaurant LiveData should be non-null`() {
        // GIVEN
        val viewModel = DetailViewModel(mockRepository)
        val restaurant = Restaurant(id = 123)

        // WHEN
        `when`(mockRepository.getRestaurant(id = 123)).thenReturn(Observable.just(restaurant))
        viewModel.getRestaurant(id = 123) // execute "API call"

        // THEN
        Truth.assert_()
            .that(viewModel.restaurant.value)
            .isEqualTo(restaurant)
    }

    @Test
    fun `Given a failed getRestaurant() API call, restaurant LiveData should be null`() {
        // GIVEN
        val viewModel = DetailViewModel(mockRepository)

        // WHEN
        `when`(mockRepository.getRestaurant(id = -1)).thenReturn(Observable.error(Exception()))
        viewModel.getRestaurant(id = -1) // execute "API call"

        // THEN
        Truth.assert_()
            .that(viewModel.restaurant.value)
            .isEqualTo(null)
    }
}