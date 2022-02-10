package com.coding.challenge.data.dao

import androidx.room.TypeConverter
import com.coding.challenge.data.dto.Data
import com.coding.challenge.data.dto.ProductDetailsData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    // You can use any type of list instead of using string list

    @TypeConverter
    fun fromStringToPriceList(value: String): List<Data.Price> {
        val listType = object : TypeToken<List<Data.Price>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringToPromotionList(value: String): List<Data.Promotion> {
        val listType = object : TypeToken<List<Data.Promotion>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringToRegularPriceList(value: String): List<Data.RegularPrice> {
        val listType = object : TypeToken<List<Data.RegularPrice>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringToSalePriceList(value: String): List<Data.SalePrice> {
        val listType = object : TypeToken<List<Data.SalePrice>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringToDataList(value: String): List<Data> {
        val listType = object : TypeToken<List<Data>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringToProductDetailsDataList(value: String): ProductDetailsData {
        val objType = object : TypeToken<ProductDetailsData>() {}.type
        return Gson().fromJson(value, objType)
    }

    @TypeConverter
    fun fromStringToProductDetailsImageList(value: String): List<ProductDetailsData.Image> {
        val listType = object : TypeToken<List<ProductDetailsData.Image>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromProductDetailsResponseImageListToString(list: List<ProductDetailsData.Image>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromProductDetailsResponseDataListToString(list: List<ProductDetailsData>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromDataListToString(list: List<Data>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromPriceListToString(list: List<Data.Price>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromPromotionListToString(list: List<Data.Promotion>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
    @TypeConverter
    fun fromRegularPriceListToString(list: List<Data.RegularPrice>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
    @TypeConverter
    fun fromSalePriceListToString(list: List<Data.SalePrice>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}