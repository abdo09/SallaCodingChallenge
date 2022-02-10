package com.coding.challenge.data.dto

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ProductDetailsData(
    @Json(name = "brand")
    @Embedded
    var productDetailsDataBrand: Brand? = Brand(),
    @Json(name = "description")
    @ColumnInfo(name = "productDetailsDescription")
    var description: String? = "", // <p><strong>أفضل دورة تدربية حتشوفها في حياتك</strong></p><p><strong></strong><img src="https://s3-eu-central-1.amazonaws.com/salla-cdn/MvKEKzwrZNHrW3dgOhbjePFOxhbNUvwa0f1CfJeF.jpeg"></p>
    @Json(name = "features")
    @Embedded
    var features: Features? = Features(),
    @Json(name = "has_special_price")
    var hasSpecialPrice: Boolean? = false, // false
    @Json(name = "id")
    @ColumnInfo(name = "dataId")
    var id: Int? = 0, // 492449845
    @Json(name = "images")
    var images: List<Image>? = listOf(),
    @Json(name = "included_tax")
    var includedTax: Boolean? = false, // false
    @Json(name = "is_available")
    var isAvailable: Boolean? = false, // true
    @Json(name = "managed_by_branches")
    var managedByBranches: Boolean? = false, // false
    @Json(name = "max_items_per_user")
    var maxItemsPerUser: Int? = 0, // 10
    @Json(name = "name")
    @ColumnInfo(name = "dataName")
    var name: String? = "", // عطر بلاك اوبما 50 مل
    @Json(name = "pre_tax_price")
    @Embedded
    var preTaxPrice: PreTaxPrice? = PreTaxPrice(),
    @Json(name = "price")
    @Embedded
    var price: Price? = Price(),
    @Json(name = "promotion")
    @Embedded
    var promotion: Promotion? = Promotion(),
    @Json(name = "quantity")
    var quantity: Long? = 0, // 3
    @Json(name = "rating")
    @Embedded
    var rating: Rating? = Rating(),
    @Json(name = "regular_price")
    @Embedded
    var regularPrice: RegularPrice? = RegularPrice(),
    @Json(name = "require_shipping")
    var requireShipping: Boolean? = false, // true
    @Json(name = "sale_end")
    var saleEnd: String? = "", // 2019-07-09
    @Json(name = "sale_price")
    @Embedded
    var salePrice: SalePrice? = SalePrice(),
    @Json(name = "short_link_code")
    var shortLinkCode: String? = "", // rAwWOy
    @Json(name = "show_in_app")
    var showInApp: Boolean? = false, // true
    @Json(name = "show_purchase_count")
    var showPurchaseCount: Boolean? = false, // false
    @Json(name = "sold_quantity")
    var soldQuantity: String? = "", // 12
    @Json(name = "sold_quantity_desc")
    var soldQuantityDesc: String? = "", // 12 مرة
    @Json(name = "status")
    var status: String? = "", // sale
    @Json(name = "tax")
    @Embedded
    var tax: Tax? = Tax(),
    @Json(name = "taxed_price")
    @Embedded
    var taxedPrice: TaxedPrice? = TaxedPrice(),
    @Json(name = "type")
    var type: String? = "", // service
    @Json(name = "unlimited_quantity")
    var unlimitedQuantity: Boolean? = false, // false
    @Json(name = "url")
    var url: String? = "", // https://salla.sa/developers/rAwWOy
    @Json(name = "urls")
    @Embedded
    var urls: Urls? = Urls(),
    @Json(name = "weight")
    var weight: Int? = 0, // 0
    @Json(name = "with_tax")
    var withTax: Boolean? = false // true
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Brand(
        @Json(name = "ar_char")
        var arChar: String? = "", // ا
        @Json(name = "banner")
        var banner: String? = "", // https://cdn.salla.sa/ydZbx/Cd3r4vxB4qFLttoKMpRvwBVxgpsS5LSyza5QlhK4.jpeg
        @Json(name = "description")
        @ColumnInfo(name = "brandDescription")
        var description: String? = "", // Italian designer Giorgio Armani's name is synonymous with impeccable taste and classic style, and his line of fragrances is no different. Men can choose from a range of eau de toilettes like Acqua Di Gio and Code for Men, or upgrade to the hallmark Emporio Armani and Emporio Armani Diamonds colognes
        @Json(name = "en_char")
        var enChar: String? = "", // a
        @Json(name = "id")
        @ColumnInfo(name = "brandId")
        var id: Int? = 0, // 259940351
        @Json(name = "logo")
        var logo: String? = "", // https://cdn.salla.sa/ydZbx/nfcOPzCmwKaGglktI20fNJe2WOkPz1q5GRt3IDBe.png
        @Json(name = "name")
        @ColumnInfo(name = "brandName")
        var name: String? = "" // Armani
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Features(
        @Json(name = "availability_notify")
        @Embedded
        var availabilityNotify: AvailabilityNotify? = AvailabilityNotify(),
        @Json(name = "show_rating")
        var showRating: Boolean? = false, // true
        @Json(name = "show_remaining_quantity")
        var showRemainingQuantity: Boolean? = false, // true
        @Json(name = "show_you_may_like")
        var showYouMayLike: Boolean? = false // true
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class AvailabilityNotify(
            @Json(name = "email")
            var email: Boolean? = false, // true
            @Json(name = "mobile")
            var mobile: Boolean? = false, // true
            @Json(name = "sms")
            var sms: Boolean? = false // true
        ) : Parcelable
    }

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Image(
        @Json(name = "alt")
        var alt: String? = "",
        @Json(name = "id")
        @ColumnInfo(name = "imageId")
        var id: Int? = 0, // 38998776
        @Json(name = "sort")
        var sort: Int? = 0, // 19
        @Json(name = "type")
        var type: String? = "", // image
        @Json(name = "url")
        var url: String? = "", // https://cdn.salla.sa/9C1KipNJMEaWdGdWQui05FTX6FxiOnpM5bl9bhzJ.jpeg
        @Json(name = "video_url")
        var videoUrl: String? = ""
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class PreTaxPrice(
        @Json(name = "amount")
        @ColumnInfo(name = "amountPreTaxPrice")
        var amount: Double? = 0.0, // 1
        @Json(name = "currency")
        @ColumnInfo(name = "currencyPreTaxPrice")
        var currency: String? = "" // SAR
    ) : Parcelable

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
    data class Rating(
        @Json(name = "count")
        var count: Double? = 0.0, // 1
        @Json(name = "rate")
        var rate: Double? = 0.0, // 0
        @Json(name = "total")
        var total: Double? = 0.0 // 0
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

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Tax(
        @Json(name = "amount")
        @ColumnInfo(name = "amountTax")
        var amount: Double? = 0.0, // 0.1
        @Json(name = "currency")
        @ColumnInfo(name = "currencyTax")
        var currency: String? = "" // SAR
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class TaxedPrice(
        @Json(name = "amount")
        @ColumnInfo(name = "amountTaxedPrice")
        var amount: Double? = 0.0, // 1.1
        @Json(name = "currency")
        @ColumnInfo(name = "currencyTaxedPrice")
        var currency: String? = "" // SAR
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Urls(
        @Json(name = "admin")
        var admin: String? = "", // https://s.salla.sa/products/492449845
        @Json(name = "customer")
        var customer: String? = "" // https://salla.sa/developers/rAwWOy
    ) : Parcelable
}