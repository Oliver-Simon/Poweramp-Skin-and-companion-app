package com.maxmpz.poweramp.companion

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * A simple 24-bar chart that visualises play counts per hour of day.
 * Bars are drawn using Canvas; the tallest bar fills 80% of the view height.
 * Tapping shows nothing — it's purely informational.
 */
class HourlyBarChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val barPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    private val labelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 22f
    }
    private val highlightPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private var data = IntArray(24)
    private var maxVal = 1

    // Resolved from theme colours
    private var colorPrimary = 0xFF6200EE.toInt()
    private var colorSurface = 0xFFBBB3FF.toInt()
    private var colorOnSurface = 0xFF000000.toInt()

    init {
        // Resolve theme colours
        val ta = context.obtainStyledAttributes(
            intArrayOf(
                com.google.android.material.R.attr.colorPrimary,
                com.google.android.material.R.attr.colorPrimaryContainer,
                android.R.attr.textColorPrimary
            )
        )
        colorPrimary  = ta.getColor(0, colorPrimary)
        colorSurface  = ta.getColor(1, colorSurface)
        colorOnSurface = ta.getColor(2, colorOnSurface)
        ta.recycle()

        barPaint.color = colorSurface
        highlightPaint.color = colorPrimary
        labelPaint.color = (colorOnSurface and 0x00FFFFFF) or 0x99000000.toInt()
    }

    fun setData(hours: IntArray) {
        data = hours.copyOf(24)
        maxVal = data.max().coerceAtLeast(1)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()
        val topPad  = h * 0.05f
        val botPad  = h * 0.18f  // space for hour labels at bottom
        val chartH  = h - topPad - botPad
        val barW    = w / 24f
        val gap     = barW * 0.15f
        val peak    = data.max().toFloat().coerceAtLeast(1f)

        for (i in 0..23) {
            val barH   = (data[i] / peak) * chartH * 0.85f
            val left   = i * barW + gap
            val right  = (i + 1) * barW - gap
            val top    = topPad + chartH - barH
            val bottom = topPad + chartH

            val isNight = i < 6 || i >= 22
            val isPeak  = data[i] == data.max() && data[i] > 0
            val paint   = if (isPeak) highlightPaint else barPaint

            canvas.drawRoundRect(RectF(left, top, right, bottom), gap, gap, paint)

            // Hour label every 3 hours: 0, 3, 6 … 21
            if (i % 3 == 0) {
                labelPaint.alpha = if (isNight) 100 else 160
                canvas.drawText("${i}h", left + (right - left) / 2, h - 4f, labelPaint)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = MeasureSpec.getSize(widthMeasureSpec)
        // fixed aspect ratio ~3:1
        setMeasuredDimension(w, (w / 3.5f).toInt().coerceAtLeast(140))
    }
}
