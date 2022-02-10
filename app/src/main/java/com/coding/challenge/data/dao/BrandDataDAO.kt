package com.coding.challenge.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.coding.challenge.data.dto.BrandsResponse
import com.coding.challenge.data.dto.Data

@Dao
abstract class BrandDataDAO : BaseDao<BrandsResponse?> {

    @Query("select * from ${BrandsResponse.TABLE_NAME} WHERE brandsResponseID =:id")
    abstract fun getBrandItem(id: Int): LiveData<BrandsResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsert(data: BrandsResponse)

}