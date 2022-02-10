package com.coding.challenge.ui.common

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi


@RequiresApi(Build.VERSION_CODES.M)
class CustomTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr){

    var mText = ""
    var mColor:String? = ""

    var paint: Paint = Paint()
    var textPaint: TextPaint = TextPaint()
    var textS = 12 * resources.displayMetrics.density

    private var w = 0
    private var h = 0

    init {
        paint.isAntiAlias = true
        textPaint.isAntiAlias = true
        textPaint.textAlign  = Paint.Align.CENTER
        textPaint.textSize = textS
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        w = (MeasureSpec.getSize(widthMeasureSpec))
        h = MeasureSpec.getSize(heightMeasureSpec)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mColor?.isNotEmpty() == true){
            paint.color = Color.parseColor(mColor)
        }
        textPaint.color = Color.WHITE
        canvas.rotate(-45f)
        val rect = Rect(-w, (h/2), w, (h/3))
        canvas.drawRect(rect, paint)
        canvas.drawText(mText, rect.centerX().toFloat(), rect.centerY().toFloat() + 5 ,textPaint)
    }

}