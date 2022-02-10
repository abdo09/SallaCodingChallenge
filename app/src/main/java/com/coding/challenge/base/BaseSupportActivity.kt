package com.coding.challenge.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.coding.challenge.R
import com.coding.challenge.utils.ContextWrapper
import com.coding.challenge.utils.navigationBarAndStatusBarColor
import java.util.*

abstract class BaseSupportActivity: AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        val lang = "en"
        val myLocale = Locale(lang)
        val context: Context = ContextWrapper.wrap(newBase, myLocale)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.navigationBarAndStatusBarColor(R.color.warm_grey, R.color.warm_grey)

    }

}