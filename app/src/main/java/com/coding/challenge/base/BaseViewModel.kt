package com.coding.challenge.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coding.challenge.data.client.network.SingleLiveEvent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel: ViewModel(), CoroutineScope {

    //Coroutine's background job
    private val job = Job()

    //start with a logged of user
    val userLogged = MutableLiveData<Boolean>()

    val showLoading = SingleLiveEvent<Boolean>()
    val showError = SingleLiveEvent<Any>()
    val showInfo = SingleLiveEvent<Any>()
    val showSuccess = SingleLiveEvent<Any>()

    //Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()

        job.cancel()

        if (coroutineContext.isActive){
            coroutineContext.cancel()
        }
    }
}