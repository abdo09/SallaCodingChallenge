package com.coding.challenge.ui.brands


import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import com.coding.challenge.R
import com.coding.challenge.base.BaseSupportFragment
import com.coding.challenge.data.dto.BrandsResponse
import com.coding.challenge.databinding.FragmentBrandsBinding
import kotlinx.android.synthetic.main.fragment_brands.*
import org.koin.android.viewmodel.ext.android.viewModel
import com.coding.challenge.utils.*


class BrandsFragment : BaseSupportFragment(R.layout.fragment_brands) {

    override val viewModel by viewModel<BrandsViewModel>()

    private lateinit var brandAdapter: BrandAdapter
    private lateinit var binding: FragmentBrandsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBrandsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModelObserver()

        setOnclickListener()

        addCallBackToExit()

        detectTheBottomOfThePage()

        fetchData()

        progress_bar.indeterminateTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.light_black))

    }

    private fun fetchData() {
        viewModel.brandDataDAO.getBrandItem(0).observe(viewLifecycleOwner, {
            if (it == null){
                viewModel.getBrand()
            } else {
                viewModel.brandResponse.postValue(it)
            }
        })
    }

    private fun setOnclickListener() {
        brandAdapter.setOnItemClickListener {
            setAppBarVisibilityAndTitle(View.VISIBLE, "")
            navController.navigate(BrandsFragmentDirections.actionBrandsFragmentToProductDetailsFragment(it))
        }

        brandAdapter.setOnAddedToCart {
            CookieBarConfig(requireActivity()).showDefaultSuccessCookie("Added to cart successfully")
        }
    }

    private fun viewModelObserver() {

        viewModel.brandResponse.observe(viewLifecycleOwner, {
            initViews(it)
            brandAdapter.differ.submitList(it.data?.toList())
        })

        viewModel.loadNextPage.observe(viewLifecycleOwner, {
            if (it){
                binding.progressBar.fadeIn(150)
            }else {
                binding.progressBar.fadeOut(150)
            }
        })
    }

    private fun setupRecyclerView() {
        brandAdapter = BrandAdapter()
        binding.rvProducts.apply {
            adapter = brandAdapter
        }
    }

    private fun initViews(brandsResponse: BrandsResponse?){
        brandsResponse?.let {
            binding.brand = it
            if (!it.brand?.banner.isNullOrEmpty()){
                binding.brandBannerCardView.fadeIn(300)
            }
            if (!it.brand?.logo.isNullOrEmpty()){
                binding.brandLogo.fadeIn(300)
            }
        }
        setAppBarVisibilityAndTitle(View.VISIBLE, brandsResponse?.brand?.name.toString())
    }

    private fun addCallBackToExit() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callBack)
    }

    var lastCallback: Long = 0
    private var callBack: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (System.currentTimeMillis() - lastCallback < 3000) {
                requireActivity().finish()
            } else {
                viewModel.showInfo.value = R.string.press_back_again_to_exit
                lastCallback = System.currentTimeMillis()
            }
        }
    }

    private fun detectTheBottomOfThePage(){
        binding.brandNestedScroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if(scrollY ==v?.getChildAt(0)?.measuredHeight?.minus(v.measuredHeight)){
                loadNextPage()
            }
        })
    }

    private fun loadNextPage(){
        if (!viewModel.brandResponse.value?.cursor?.next.isNullOrEmpty()){
            viewModel.brandResponse.value?.cursor?.current?.plus(1)?.let {
                viewModel.getBrand(page = it)
            }
        }
    }

}