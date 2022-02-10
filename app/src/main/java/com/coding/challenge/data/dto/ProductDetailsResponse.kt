package com.coding.challenge.data.dto


import android.annotation.SuppressLint
import com.squareup.moshi.Json
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coding.challenge.data.dto.ProductDetailsResponse.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Entity(tableName = TABLE_NAME)
data class ProductDetailsResponse(
    @PrimaryKey
    @ColumnInfo(name = "productDetailsResponseID")
    var id: Int = 0,
    @Json(name = "data")
    @Embedded
    var productDetailsData: ProductDetailsData? = ProductDetailsData(),
    @Json(name = "status")
    @ColumnInfo(name = "productDetailsStatus")
    var status: Int? = 0, // 200
    @Json(name = "success")
    var success: Boolean? = false // true
) : Parcelable {
    companion object {
        const val TABLE_NAME = "product_details_table"
    }
}