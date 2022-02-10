package com.coding.challenge.di

import com.coding.challenge.data.client.AuthInterceptor
import com.coding.challenge.data.client.createHttpClient
import com.coding.challenge.data.client.createRetrofit
import com.coding.challenge.data.client.createWebService
import com.coding.challenge.data.services.ProductServices
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val networkModule = module {
    //create webservices definitions
    single {
        AuthInterceptor(androidApplication())
    }
    single {
        createHttpClient(get())
    }

    single { createRetrofit(get()) }

    factory { createWebService<ProductServices>(get()) }
}

