@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.coding.challenge.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_product_details.*
import java.text.SimpleDateFormat
import java.util.*

fun Activity.navigationBarAndStatusBarColor(
    @ColorRes statusColor: Int,
    @ColorRes navigationColor: Int
) {
    val window: Window = this.window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ContextCompat.getColor(this, statusColor)
    window.navigationBarColor = ContextCompat.getColor(this, navigationColor)
}
fun Context.loadWithGlide(
    into: ImageView?,
    url: Any?,
    fitImage: Boolean = false,
    roundImage: Boolean = false,
    listener: RequestListener<Drawable>? = null
) {
    if (url == null)
        return
    val circularProgressDrawable = CircularProgressDrawable(this)
    circularProgressDrawable.strokeWidth = 10f
    circularProgressDrawable.centerRadius = 100f
    circularProgressDrawable.setStyle(CircularProgressDrawable.DEFAULT)
    circularProgressDrawable.start()

    val options = RequestOptions()
//            .optionalFitCenter()
        .placeholder(circularProgressDrawable.apply {
            this.backgroundColor = android.R.color.black

        })
        .skipMemoryCache(false)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .error(circularProgressDrawable.apply {
            this.backgroundColor = android.R.color.holo_red_dark
        })
        .priority(Priority.HIGH)


    if (fitImage)
        options.fitCenter()
    if (roundImage)
        options.transform(RoundedCorners(16))



    try {
        var imgURI = "" //RemoteConfigs.BASE_URL

        val glide = Glide.with(this).asDrawable()

        if (url is String) {
            when {
                url.startsWith("/data") -> imgURI = url
                url.startsWith("/") -> imgURI = url.replaceFirst("/", "https://")
                url.startsWith("http:") -> imgURI = url.replace("http", "https")
                url.startsWith("https") -> imgURI = url
                else -> imgURI += url
            }

            if (into != null) {
                glide.load(imgURI).apply(options)
                    .listener(listener)
                    .into(into)
            }
        } else {
            if (into != null) {
                glide.load(url).apply(options)
                    .listener(listener)
                    .into(into)
            }

        }

    } catch (ex: Exception) {
        ex.printStackTrace()
    }

}

fun Int.spToFloat(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )
}

fun Context.getColorCompat(colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(photoUrl: String?){
    this.context.apply {
        loadWithGlide(this@loadImageFromUrl, photoUrl?: "")
    }
}
val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun drawable(backgroundColor: String, topLeftCorner: Int = 0, topRightCorner: Int = 0, bottomLeftCorner: Int = 0, bottomRightCorner: Int = 0): GradientDrawable {
    val drawables = GradientDrawable()
    val topLeft = topLeftCorner.dpToPx.toFloat()
    val topRight = topRightCorner.dpToPx.toFloat()
    val bottomLeft = bottomLeftCorner.dpToPx.toFloat()
    val bottomRight = bottomRightCorner.dpToPx.toFloat()

    drawables.setColor(
        Color.parseColor(
            backgroundColor
        )
    )

    drawables.cornerRadii = floatArrayOf(
        // top left
        topLeft,
        topLeft,
        // top right
        topRight,
        topRight,
        // bottom right
        bottomRight,
        bottomRight,
        // bottom left
        bottomLeft,
        bottomLeft
    )
    return drawables
}

fun View.fadeIn(duration: Long = 600) {
    if (visibility != View.VISIBLE)
        this.apply {
            alpha = 0f
            visibility = View.VISIBLE
            post {
                animate().alpha(1f).setDuration(duration).start()
            }
        }

}

fun View.fadeOut(duration: Long = 800) {
    if (visibility != View.GONE)
        this.apply {
            alpha = 1f
            post {
                animate().alpha(0f).setDuration(duration)
                    .withEndAction {
                        visibility = View.GONE
                    }.start()
            }
        }
}

fun Context.isTablet(): Boolean {
    val xlarge =
        this.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == 4
    val large =
        this.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE
    return xlarge || large
}

fun setTextSize(isTablet: Boolean, text: String?, textView: TextView){
    if (isTablet){
        if (text?.length?.let { it > 20 } == true) {
            textView.textSize = 14.spToFloat()
        } else if (text?.length?.let { it in 14..19 } == true) {
            textView.textSize = 16.spToFloat()
        }
    } else {
        if (text?.length?.let { it > 20 } == true) {
            textView.textSize = 3.spToFloat()
        } else if (text?.length?.let { it in 14..19 } == true) {
            textView.textSize = 3.spToFloat()
        }
    }
}
fun setTextSize(isTablet: Boolean = false, text: String?, textView: TextView, isProductDetails: Boolean){
    if (isTablet){
        when {
            text?.length?.let { it in 20..29 } == true -> {
                textView.textSize = 14.spToFloat()
            }
            text?.length?.let { it in 16..19 } == true -> {
                textView.textSize = 16.spToFloat()
            }
            text?.length?.let { it > 29 } == true -> {
                textView.textSize = 10.spToFloat()
            }
            else -> {
                textView.textSize = 18.spToFloat()
            }
        }
    } else {
        when {
            text?.length?.let { it in 20..29 } == true -> {
                textView.textSize = 4.spToFloat()
            }
            text?.length?.let { it in 16..19 } == true -> {
                textView.textSize = 5.spToFloat()
            }
            text?.length?.let { it > 29 } == true -> {
                textView.textSize = 3.spToFloat()
            }
            else -> {
                textView.textSize = 7.spToFloat()
            }
        }
    }
}