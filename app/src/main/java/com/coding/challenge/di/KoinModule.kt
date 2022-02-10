package com.coding.challenge.di


import com.coding.challenge.data.repository.ProductRepository
import com.coding.challenge.data.repository.ProductRepositoryImp
import com.coding.challenge.ui.brands.BrandsViewModel
import com.coding.challenge.ui.productDetails.ProductDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

     viewModel { ProductDetailsViewModel(get(), get()) }
     viewModel { BrandsViewModel(get(), get()) }

     factory<ProductRepository> { ProductRepositoryImp(get(), get()) }
}