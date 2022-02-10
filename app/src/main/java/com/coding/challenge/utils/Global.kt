package com.coding.challenge.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat

@SuppressLint("ObsoleteSdkInt")
fun Context.getCustomColor(context: Context, color: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(color, theme)
    } else {
        ContextCompat.getColor(context, color)
    }
}