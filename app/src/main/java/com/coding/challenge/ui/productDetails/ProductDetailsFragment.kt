package com.coding.challenge.ui.productDetails

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.coding.challenge.R
import com.coding.challenge.base.BaseSupportFragment
import com.coding.challenge.data.dto.ProductDetailsResponse
import com.coding.challenge.databinding.FragmentProductDetailsBinding
import kotlinx.android.synthetic.main.fragment_product_details.*
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.challenge.ui.common.CirclePagerIndicatorDecoration
import com.coding.challenge.utils.*


class ProductDetailsFragment : BaseSupportFragment(R.layout.fragment_product_details) {

    override val viewModel by viewModel<ProductDetailsViewModel>()

    private val args by navArgs<ProductDetailsFragmentArgs>()
    private lateinit var snapHelper: PagerSnapHelper

    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        snapHelper = PagerSnapHelper()

        fetchData()
        viewModelObserver()
        addCallBackToExit()

    }

    private fun fetchData() {
        viewModel.productDetailsDAO.getProductItem(args.product?.id ?: 0)
            .observe(viewLifecycleOwner) {
                if (it == null) {
                    viewModel.getProductDetails(args.product?.id.toString())
                } else {
                    viewModel.productDetailsResponse.postValue(it)
                }
            }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews(productDetailsResponse: ProductDetailsResponse?) {
        productDetailsResponse?.let { binding.productDetails = it }

        //Toolbar title
        setAppBarVisibilityAndTitle(
            View.VISIBLE,
            productDetailsResponse?.productDetailsData?.name.toString()
        )

        // Initiate image slider and indicator
        initImageSliderAndIndicators(productDetailsResponse)

        // Initiate promotion layout
        initPromotionLayout()

    }

    private fun viewModelObserver() {
        viewModel.productDetailsResponse.observe(viewLifecycleOwner) {
            initViews(it)
        }
    }

    private fun addCallBackToExit() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callBack)
    }

    private val callBack: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            navController.navigate(ProductDetailsFragmentDirections.actionProductDetailsFragmentToBrandsFragment())
        }

    }

    private fun initPromotionLayout() {
        if (!args.product?.promotion?.subTitle.isNullOrEmpty()) {
            binding.customText.apply {
                visibility = View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mColor = "#575757"
                    this.mText = args.product?.promotion?.subTitle?: ""
                }
            }
        }
    }

    private fun initImageSliderAndIndicators(productDetailsResponse: ProductDetailsResponse?) {
        binding.cardDetails.visibility = View.VISIBLE
        val images = productDetailsResponse?.productDetailsData?.images?.chunked(30)

        viewPagerAdapter = ViewPagerAdapter(images?.get(0) ?: listOf())

        val circlePagerIndicatorDecoration = CirclePagerIndicatorDecoration()
        circlePagerIndicatorDecoration.apply {
            colorActive = "#575757"
            colorInactive = "#C6C6C6"
        }

        binding.slidingViewPager.apply {
            adapter = viewPagerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(circlePagerIndicatorDecoration)
        }
        snapHelper.attachToRecyclerView(sliding_view_pager)
    }

}