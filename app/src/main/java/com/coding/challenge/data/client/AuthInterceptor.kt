package com.coding.challenge.data.client

import android.app.Application
import com.coding.challenge.utils.Constants
import com.auth0.android.jwt.JWT
import okhttp3.*
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Retrofit
import timber.log.Timber
import java.lang.Exception

class AuthInterceptor(val context: Application) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val mToken = Constants().getAccessToken(context) ?: ""

        val jtw = try {
            JWT(mToken)
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
        // This is a synchronous call
        val request = chain.request().newBuilder()
            .addHeaders(mToken)
            .build()
        return chain.proceed(request)

    }

    private fun Request.Builder.addHeaders(token: String?) = this.apply { header("Authorization", "Bearer $token") }


}