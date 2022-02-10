package com.coding.challenge.data.repository

import androidx.lifecycle.LiveData
import com.coding.challenge.data.client.network.UseCaseResult
import com.coding.challenge.data.dao.BrandDataDAO
import com.coding.challenge.data.dto.BrandsResponse
import com.coding.challenge.data.dto.ProductDetailsResponse
import com.coding.challenge.data.services.ProductServices

interface ProductRepository {
    suspend fun getBrands(page: Int, per_page: Int): UseCaseResult<BrandsResponse>
    suspend fun getProductDetails(id: String): UseCaseResult<ProductDetailsResponse>

    fun getBrandsFromDB(id: Int): LiveData<BrandsResponse>
    fun upsertBrand(brand: BrandsResponse)
}

class ProductRepositoryImp(private val productServices: ProductServices, private val brandDataDAO: BrandDataDAO) : ProductRepository {

    override suspend fun getBrands(page: Int, per_page: Int): UseCaseResult<BrandsResponse> {
        val params = mapOf(
            "page" to page,
            "per_page" to per_page
        )
        return try {
            UseCaseResult.OnSuccess(productServices.getBrands(params))
        } catch (ex: Exception) {
            UseCaseResult.OnError(ex)
        }
    }

    override suspend fun getProductDetails(id: String): UseCaseResult<ProductDetailsResponse> {
        return try {
            UseCaseResult.OnSuccess(productServices.getProductDetails(id))
        } catch (ex: Exception) {
            UseCaseResult.OnError(ex)
        }
    }

    override fun getBrandsFromDB(id: Int): LiveData<BrandsResponse> {
        return brandDataDAO.getBrandItem(id)
    }

    override fun upsertBrand(brand: BrandsResponse) {
        return brandDataDAO.upsert(brand)
    }

}