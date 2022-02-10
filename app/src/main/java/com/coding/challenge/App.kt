package com.coding.challenge

import android.content.Context
import android.content.res.Configuration
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.coding.challenge.di.appModules
import com.coding.challenge.di.dataModule
import com.coding.challenge.di.networkModule
import com.coding.challenge.utils.Constants
import com.google.firebase.FirebaseApp
import com.coding.challenge.utils.CrashReportingTree
import com.coding.challenge.utils.LocalizationUtil
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App: MultiDexApplication() {

    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        //firebase initialization on the app level
        FirebaseApp.initializeApp(this)

        configureTimber()

        configureKoin()

        setDefaultLanguage()

    }

    private fun configureTimber() {
        //Timber used for logging
        // Logging in Debug build, in release log only crashes
        if (BuildConfig.FLAVOR == "development" || BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        else
            Timber.plant(CrashReportingTree())
        Timber.d("BuildConfig.DEBUG ${BuildConfig.DEBUG} ${BuildConfig.BUILD_TYPE} ${BuildConfig.FLAVOR}")
    }

    private fun configureKoin() {
        startKoin {
            // use the Android context given there
            androidContext(this@App)
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger(Level.ERROR)
            // load properties from assets/koin.properties file
            androidFileProperties()

            modules(listOf(dataModule, appModules, networkModule))
        }
    }

    private fun setDefaultLanguage() {
        val lang = Constants().getCurrentLanguage(applicationContext)
        LocalizationUtil.setLanguage(lang, this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        setDefaultLanguage()
        super.onConfigurationChanged(newConfig)
        setDefaultLanguage()
    }
}