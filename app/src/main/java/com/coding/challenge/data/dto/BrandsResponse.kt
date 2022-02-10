package com.coding.challenge.data.dto


import android.annotation.SuppressLint
import com.squareup.moshi.Json
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.coding.challenge.data.dto.BrandsResponse.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Entity(tableName = TABLE_NAME)
data class BrandsResponse(
    @PrimaryKey
    @ColumnInfo(name = "brandsResponseID")
    var id: Int = 0,
    @Json(name = "brand")
    @Embedded
    var brand: Brand? = Brand(),
    @Json(name = "cursor")
    @Embedded
    var cursor: Cursor? = Cursor(),
    @Json(name = "data")
    var `data`: MutableList<Data?>? = mutableListOf(),
    @Json(name = "status")
    var status: Int? = 0, // 200
    @Json(name = "success")
    var success: Boolean? = false // true
) : Parcelable {
    companion object {
        const val TABLE_NAME = "brands_table"
    }
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Brand(
        @Json(name = "ar_char")
        var arChar: String? = "", // ا
        @Json(name = "banner")
        var banner: String? = "", // https://cdn.salla.sa/ydZbx/Cd3r4vxB4qFLttoKMpRvwBVxgpsS5LSyza5QlhK4.jpeg
        @Json(name = "description")
        var description: String? = "", // Italian designer Giorgio Armani's name is synonymous with impeccable taste and classic style, and his line of fragrances is no different. Men can choose from a range of eau de toilettes like Acqua Di Gio and Code for Men, or upgrade to the hallmark Emporio Armani and Emporio Armani Diamonds colognes
        @Json(name = "en_char")
        var enChar: String? = "", // a
        @Json(name = "id")
        @ColumnInfo(name = "brandId")
        var id: Int? = 0, // 259940351
        @Json(name = "logo")
        var logo: String? = "", // https://cdn.salla.sa/ydZbx/nfcOPzCmwKaGglktI20fNJe2WOkPz1q5GRt3IDBe.png
        @Json(name = "name")
        var name: String? = "" // Armani
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Cursor(
        @Json(name = "count")
        var count: Int? = 0, // 5
        @Json(name = "current")
        var current: Int? = 0, // 1
        @Json(name = "next")
        var next: String? = "" // https://salla.sa/api/v1/brands/259940351?page=2
    ) : Parcelable

}