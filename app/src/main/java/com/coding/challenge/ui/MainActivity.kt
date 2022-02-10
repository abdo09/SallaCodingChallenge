package com.coding.challenge.ui

import android.os.Bundle
import android.widget.Toast
import com.coding.challenge.R
import com.coding.challenge.base.BaseSupportActivity
import com.coding.challenge.utils.Constants
import com.coding.challenge.utils.isTablet
import timber.log.Timber

class MainActivity : BaseSupportActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isTablet = this.isTablet()
        Constants().isTablet(this, isTablet)
    }
}