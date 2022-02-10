package com.coding.challenge.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.coding.challenge.R
import com.coding.challenge.di.getSharedPrefs


class Constants {

    fun isTablet(context: Context): Boolean {
        return getSharedPrefs(context).getBoolean(context.getString(R.string.pref_is_tablet), false)
    }

    fun isTablet(context: Context, isTablet: Boolean) {
        getSharedPrefs(context).edit().putBoolean(context.getString(R.string.pref_is_tablet), isTablet).apply()
    }

    fun getAccessToken(context: Context): String? {
        return getSharedPrefs(context).getString(context.getString(R.string.pref_access_token), "")
    }

    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String? {
        return getSharedPrefs(context).getString(context.getString(R.string.pref_device_id), Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID))
    }

    fun getCurrentLanguage(context: Context): String? {
        return getSharedPrefs(context).getString(context.getString(R.string.pref_language), "en")
    }

}

