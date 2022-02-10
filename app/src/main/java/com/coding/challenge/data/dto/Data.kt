package com.coding.challenge.data.dto

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Data(
    @Json(name = "currency")
    var currency: String? = "", // SAR
    @Json(name = "has_special_price")
    var hasSpecialPrice: Boolean? = false, // false
    @Json(name = "id")
    var id: Int = 0, // 492449845
    @Json(name = "is_available")
    var isAvailable: Boolean? = false, // true
    @Json(name = "name")
    var name: String? = "", // عطر بلاك اوبما 50 مل
    @Json(name = "price")
    @Embedded
    var price: Price? = Price(),
    @Json(name = "promotion")
    @Embedded
    var promotion: Promotion? = Promotion(),
    @Json(name = "regular_price")
    @Embedded
    var regularPrice: RegularPrice? = RegularPrice(),
    @Json(name = "sale_price")
    @Embedded
    var salePrice: SalePrice? = SalePrice(),
    @Json(name = "sku")
    var sku: String? = "",
    @Json(name = "status")
    var status: String? = "", // sale
    @Json(name = "thumbnail")
    var thumbnail: String? = "", // https://cdn.salla.sa/SimNVYG2462wNuYjT6Tc021Yk0PoAwBCLrZM2iZu.jpeg
    @Json(name = "type")
    var type: String? = "", // service
    @Json(name = "url")
    var url: String? = "" // https://salla.sa/developers/rAwWOy
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Price(
        @Json(name = "amount")
        @ColumnInfo(name = "amountPrice")
        var amount: Double? = 0.0, // 1
        @Json(name = "currency")
        @ColumnInfo(name = "currencyPrice")
        var currency: String? = "" // SAR
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Promotion(
        @Json(name = "sub_title")
        var subTitle: String? = "", // عرض خاص
        @Json(name = "title")
        var title: String? = "" // تخفيض
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class RegularPrice(
        @Json(name = "amount")
        @ColumnInfo(name = "amountRegularPrice")
        var amount: Double? = 0.0, // 1
        @Json(name = "currency")
        @ColumnInfo(name = "currencyRegularPrice")
        var currency: String? = "" // SAR
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class SalePrice(
        @Json(name = "amount")
        @ColumnInfo(name = "amountSalePrice")
        var amount: Double? = 0.0, // 0
        @Json(name = "currency")
        @ColumnInfo(name = "currencySalePrice")
        var currency: String? = "" // SAR
    ) : Parcelable
}