package com.coding.challenge.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coding.challenge.data.dao.BrandDataDAO
import com.coding.challenge.data.dao.Converters
import com.coding.challenge.data.dao.ProductDetailsDAO
import com.coding.challenge.data.dto.BrandsResponse
import com.coding.challenge.data.dto.ProductDetailsResponse

@Database(
    entities = [BrandsResponse::class, ProductDetailsResponse::class], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DB : RoomDatabase() {
    abstract fun brandDataDAO(): BrandDataDAO
    abstract fun productDetailsDAO(): ProductDetailsDAO
}