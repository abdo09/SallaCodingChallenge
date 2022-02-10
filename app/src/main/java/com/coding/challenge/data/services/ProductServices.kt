package com.coding.challenge.data.services

import com.coding.challenge.data.dto.ProductDetailsResponse
import com.coding.challenge.data.dto.BrandsResponse
import retrofit2.http.*

interface ProductServices {

    @GET("brands/259940351")
    suspend fun getBrands(@QueryMap(encoded = true) params: @JvmSuppressWildcards Map<String, Any>): BrandsResponse

    @GET("products/{id}/details")
    suspend fun getProductDetails(@Path("id") id: String): ProductDetailsResponse

}