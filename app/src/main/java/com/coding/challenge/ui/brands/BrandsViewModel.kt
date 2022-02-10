package com.coding.challenge.ui.brands

import androidx.lifecycle.MutableLiveData
import com.coding.challenge.base.BaseViewModel
import com.coding.challenge.data.client.network.SingleLiveEvent
import com.coding.challenge.data.client.network.UseCaseResult
import com.coding.challenge.data.dao.BrandDataDAO
import com.coding.challenge.data.dto.BrandsResponse
import com.coding.challenge.data.dto.Data
import com.coding.challenge.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BrandsViewModel(private val productRepository: ProductRepository, val brandDataDAO: BrandDataDAO): BaseViewModel() {

    var brandResponse = SingleLiveEvent<BrandsResponse>()
    var loadNextPage = MutableLiveData<Boolean>()

    fun getBrand(page: Int = 1, per_page: Int = 5){

        if (page == 1){
            showLoading.value = true
        } else{
            loadNextPage.value = true
        }
        launch {
            val result = withContext(Dispatchers.IO) { productRepository.getBrands(page = page, per_page = per_page) }
            showLoading.value = false
            loadNextPage.value = false
            when (result) {
                is UseCaseResult.OnSuccess -> {
                    if (brandResponse.value == null){
                        brandResponse.postValue(result.data)
                    } else {
                        val oldDataList = brandResponse.value?.data
                        result.data.data?.forEach {
                            if(oldDataList?.contains(it) != true) {
                                oldDataList?.add(it)
                            }
                        }
                        result.data.data = oldDataList
                        result.data.id = 0
                        brandResponse.postValue(result.data)
                        withContext(Dispatchers.IO) { brandDataDAO.upsert(result.data) }
                    }
                      }
                is UseCaseResult.OnError -> { showError.value = result.exception.message }
            }

        }
    }

}