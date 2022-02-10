package com.coding.challenge.ui.common

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.coding.challenge.R

class ProgressDialog(context: Context?) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //setCancelable(true)
        setContentView(R.layout.prgress_dialog_layout)
    }


    init {
        setCanceledOnTouchOutside(false)
    }
}