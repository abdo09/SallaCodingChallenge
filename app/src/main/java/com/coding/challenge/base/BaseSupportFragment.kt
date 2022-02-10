package com.coding.challenge.base

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.coding.challenge.R
import com.coding.challenge.ui.MainActivity
import com.coding.challenge.ui.common.ProgressDialog
import com.coding.challenge.utils.CookieBarConfig
import com.coding.challenge.utils.getCustomColor
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.view.*


abstract class BaseSupportFragment(val fragment: Int): Fragment(fragment){

    //full screen loading dialog
    val progressDialog by lazy { ProgressDialog(activity) }

    //base viewModel for configuring info ,success, failure ,loading ,user login events
    abstract val viewModel: BaseViewModel

    //alert module , controlled through the base viewModel
    private val cookieBarConfig: CookieBarConfig by lazy { CookieBarConfig(requireActivity()) }

    //basic navigation controller for fragments within the app
    val navController by lazy { findNavController() }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNetworkConnectivity(requireContext())

        viewModel.showLoading.observe(viewLifecycleOwner, { showLoading ->
            if (activity != null && !requireActivity().isFinishing) {
                if (showLoading)
                    view.postDelayed({ progressDialog.show() }, 100)
                else
                    view.postDelayed({ progressDialog.dismiss() }, 100)
            }
        })

        viewModel.showInfo.observe(viewLifecycleOwner, { infoMessage ->
            if (infoMessage is String)
                cookieBarConfig.showDefaultInfoCookie(infoMessage)
            else
                context?.resources?.getString(infoMessage as Int)?.let {
                    cookieBarConfig.showDefaultInfoCookie(it)
                }
        })


        viewModel.showSuccess.observe(viewLifecycleOwner, { infoMessage ->
            if (infoMessage is String)
                cookieBarConfig.showDefaultSuccessCookie(infoMessage)
            else
                context?.resources?.getString(infoMessage as Int)?.let {
                    cookieBarConfig.showDefaultSuccessCookie(it)
                }
        })

        viewModel.showError.observe(viewLifecycleOwner, { showError ->
            cookieBarConfig.runCatching {
                viewModel.showLoading.postValue(false)
                if (showError is String) {
                    view.post { this.showDefaultErrorCookie(error = showError) }
                    if (showError.contains("No address associated with hostname")) {
                        val alert =
                            Snackbar.make(view, R.string.network_error, Snackbar.LENGTH_INDEFINITE)
                                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
                                .setBackgroundTint(context?.getCustomColor(requireContext(), R.color.warm_grey)!!)
                                .setTextColor(context?.getCustomColor(requireContext(), R.color.white)!!)
                        alert.setAction(R.string.retry) {
                            alert.dismiss()
                        }.setActionTextColor(context?.getCustomColor(requireContext(),R.color.white)!!)
                        alert.show()
                    } else {
                    }
                } else
                    context?.resources?.getString(showError as Int)?.let {
                        cookieBarConfig.showDefaultErrorCookie(it)
                    }
            }

        })


    }

    @SuppressLint("NewApi")
    private fun checkNetworkConnectivity(context: Context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        val isConnected: Boolean = networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

        if (!isConnected) viewModel.showError.postValue(R.string.internet_not_available)
    }

    fun setAppBarVisibilityAndTitle(visibility: Int, title: String) {
        if (activity is MainActivity) {
            val mainActivity = activity as MainActivity
            if (visibility == View.VISIBLE){
                mainActivity.toolbar_layout.tv_title.text = title
            }else if (visibility == View.GONE){
                mainActivity.toolbar_layout.tv_title.text = ""
            }
            mainActivity.app_bar_layout.visibility = visibility
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog.dismiss()
    }

}