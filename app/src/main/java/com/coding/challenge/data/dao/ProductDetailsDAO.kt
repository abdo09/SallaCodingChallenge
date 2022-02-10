package com.coding.challenge.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.coding.challenge.data.dto.BrandsResponse
import com.coding.challenge.data.dto.Data
import com.coding.challenge.data.dto.ProductDetailsResponse

@Dao
abstract class ProductDetailsDAO : BaseDao<ProductDetailsResponse?> {


    @Query("select * from ${ProductDetailsResponse.TABLE_NAME} WHERE ProductDetailsResponseId =:id")
    abstract fun getProductItem(id: Int): LiveData<ProductDetailsResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsert(data: ProductDetailsResponse)

}