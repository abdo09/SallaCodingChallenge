package com.coding.challenge.ui.productDetails

import com.coding.challenge.base.BaseViewModel
import com.coding.challenge.data.client.network.SingleLiveEvent
import com.coding.challenge.data.client.network.UseCaseResult
import com.coding.challenge.data.dao.ProductDetailsDAO
import com.coding.challenge.data.dto.ProductDetailsResponse
import com.coding.challenge.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailsViewModel(private val productRepository: ProductRepository, val productDetailsDAO: ProductDetailsDAO): BaseViewModel() {

    var productDetailsResponse = SingleLiveEvent<ProductDetailsResponse>()

    fun getProductDetails(id: String){
        showLoading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { productRepository.getProductDetails(id = id) }
            showLoading.value = false
            when (result) {
                is UseCaseResult.OnSuccess -> {
                    result.data.id = result.data.productDetailsData?.id?: 0
                    withContext(Dispatchers.IO) { productDetailsDAO.upsert(result.data) }
                    productDetailsResponse.postValue(result.data)
                }
                is UseCaseResult.OnError -> { showError.value = result.exception.message }
            }

        }
    }
}