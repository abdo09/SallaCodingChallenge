package com.coding.challenge.data.client

import com.coding.challenge.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.coding.challenge.config.RemoteConfigs
import okhttp3.Cache
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(authInterceptor: AuthInterceptor, withInterceptor: Boolean = true): OkHttpClient {
    /* ConnectionSpec.MODERN_TLS is the default value */
    val tlsSpecs: List<ConnectionSpec> = listOf(ConnectionSpec.MODERN_TLS)

    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val clientBuilder = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectionSpecs(tlsSpecs)
            //.authenticator(Authenticator)
            .addInterceptor {
                val original = it.request()
                val requestBuilder = original.newBuilder()
                requestBuilder.header("Currency", "SAR")
                requestBuilder.header("Store-Identifier", "1328842359")
                //requestBuilder.header("Authorization", "bearer $token")
                requestBuilder.header("App-version", "3.0.0")
                val request = requestBuilder
                        .build()
                return@addInterceptor it.proceed(request)
            }
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)

            .retryOnConnectionFailure(true)
            .cache(Cache(createTempDir(), 200))

    if (withInterceptor)
        clientBuilder.addInterceptor(authInterceptor)

    if (BuildConfig.FLAVOR == "development" || BuildConfig.DEBUG)
        clientBuilder.addInterceptor(logInterceptor)
    return clientBuilder.build()

}


fun createRetrofit(httpClient: OkHttpClient): Retrofit {
    
     val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    return Retrofit.Builder()
            .baseUrl(RemoteConfigs.BASE_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}


/* function to build our Retrofit service */
inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}