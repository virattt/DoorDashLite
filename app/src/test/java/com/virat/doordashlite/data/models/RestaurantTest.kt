package com.virat.doordashlite.data.models

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RestaurantTest {

    @Test
    fun `When delivery fee is zero, getFormattedDeliveryFee() should return "Free"`() {
        // GIVEN
        val deliveryFee = 0.0
        val restaurant = Restaurant(
            id = 123,
            deliveryFee = deliveryFee
        )

        // WHEN
        val formattedDeliveryFee = restaurant.getFormattedDeliveryFee()

        // THEN
        assertTrue(formattedDeliveryFee == "Free")
    }

    @Test
    fun `When delivery fee is non-zero, getFormattedDeliveryFee() should return delivery fee in USD`() {
        // GIVEN
        val deliveryFee = 10000.0 // in cents
        val restaurant = Restaurant(
            id = 123,
            deliveryFee = deliveryFee
        )

        // WHEN
        val formattedDeliveryFee = restaurant.getFormattedDeliveryFee()

        // THEN
        assertFalse(formattedDeliveryFee == "Free")
        assertTrue(formattedDeliveryFee == "$100.00")
    }
}