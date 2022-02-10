package com.coding.challenge.ui.brands

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.coding.challenge.data.dto.Data
import com.coding.challenge.databinding.BrandItemBinding
import com.coding.challenge.utils.*
class BrandAdapter : RecyclerView.Adapter<BrandAdapter.BrandViewHolderViewHolder>() {

    inner class BrandViewHolderViewHolder(val brandItemBinding: BrandItemBinding) :
        RecyclerView.ViewHolder(brandItemBinding.root) {
        fun bind(data: Data) {
            brandItemBinding.data = data
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolderViewHolder {
        return BrandViewHolderViewHolder(
            BrandItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Data) -> Unit)? = null
    private var onAddedToCart: ((Boolean) -> Unit)? = null

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BrandViewHolderViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)

        holder.brandItemBinding.apply {

            itemCardView.background = drawable("#FFFFFF", 15, 15, 15, 15)
            itemCardView.elevation = 8f
            itemCardView.setOnClickListener { onItemClickListener?.let { it(product) } }

            productImg.context.apply { loadWithGlide(productImg, product.thumbnail) }

            productNameTextView.text = product.name
            productPriceTextView.text = "SAR ${product.price?.amount.toString()}"

            productAddToCartButton.apply {
                setOnClickListener { onAddedToCart?.let { it(true) } }
                background = drawable("#575757", 10, 10, 10, 10)
                elevation = 6f
            }

            if (!product.promotion?.title.isNullOrEmpty()) {
                customText.mText = product.promotion?.title?: ""
                customText.mColor = "#575757"
                customText.visibility = View.VISIBLE
            }


            productSpecialOfferTextView.apply {
                text = product.promotion?.subTitle?: ""
                visibility = if (!product.promotion?.subTitle.isNullOrEmpty()) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
            }

        }


    }

    fun setOnItemClickListener(listener: (Data) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnAddedToCart(listener: (Boolean) -> Unit) {
        onAddedToCart = listener
    }


}