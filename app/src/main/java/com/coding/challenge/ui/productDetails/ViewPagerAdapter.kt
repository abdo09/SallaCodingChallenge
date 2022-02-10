package com.coding.challenge.ui.productDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.challenge.R
import com.coding.challenge.data.dto.ProductDetailsData
import com.coding.challenge.utils.loadWithGlide
import kotlinx.android.synthetic.main.product_view_pager_item.view.*

class ViewPagerAdapter(
    private val images: List<ProductDetailsData.Image>
): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.product_view_pager_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val image = images[position]
        holder.itemView.apply {
            viewpager_img.context.apply {
                loadWithGlide(viewpager_img, image.url)
            }
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}