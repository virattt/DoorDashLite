package com.virat.doordashlite.data.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.text.NumberFormat
import java.util.*

@JsonClass(generateAdapter = true)
@Parcelize
data class Restaurant(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "cover_img_url") val coverImageUrl: String? = null,
    @Json(name = "status") val status: String? = null,
    @Json(name = "delivery_fee") val deliveryFee: Double? = null
) : Parcelable {

    fun getFormattedDeliveryFee(): String {
        if (deliveryFee == null || deliveryFee == 0.0) {
            return "Free"
        }

        val n: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        return n.format(deliveryFee / 100.0)
    }
}