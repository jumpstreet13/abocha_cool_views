package com.abocha.coolviewsapplication.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import com.abocha.coolviewsapplication.R
import kotlin.math.ceil

private const val POINT_DEFAULT_RADIUS = 10f
private const val POINTS_DEFAULT_DISTANCE = 10f
private const val POINTS_DEFAULT_COUNT = 5
private const val PAINT_BRUSH_STROKE_WIDTH = 3f

class RiskPointsView
@JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
        textSize = 40f
        color = Color.BLACK
    }
    private val metric = textPaint.fontMetrics;
    private val textHeight = ceil(metric.descent - metric.ascent)
    private var pointsCount = 0
    private val paintBrushFilled = initPaintBrushFilled()
    private val paintBrush = initPaintBrush()
    private val points: Array<Paint>
    private var pointRadius = 0f
    private var filledPoints = 0
    private var pointsDistance = 0f

    init {
        attributeSet?.let { initAttrs(it) }
        points = Array(pointsCount) { paintBrush }
        setRiskPoints(filledPoints)
    }

    private fun initAttrs(attributeSet: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RiskPointsView)
        try {
            paintBrushFilled.color = typedArray.getColor(
                R.styleable.RiskPointsView_pointsColor,
                Color.BLACK
            )
            paintBrush.color = typedArray.getColor(
                R.styleable.RiskPointsView_emptyPointsColor,
                Color.TRANSPARENT
            )
            pointsCount = typedArray.getInteger(R.styleable.RiskPointsView_pointsCount, POINTS_DEFAULT_COUNT)
            pointRadius = typedArray.getDimension(R.styleable.RiskPointsView_pointRadius, POINT_DEFAULT_RADIUS)
            pointsDistance = typedArray.getDimension(R.styleable.RiskPointsView_pointsDistance, POINTS_DEFAULT_DISTANCE)
        } finally {
            typedArray.recycle()
        }
    }

    private fun initPaintBrush(): Paint {
        return Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = PAINT_BRUSH_STROKE_WIDTH
        }
    }

    private fun initPaintBrushFilled(): Paint {
        return Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            strokeWidth = PAINT_BRUSH_STROKE_WIDTH
        }
    }

    fun setRiskPoints(count: Int) {
        // добавить возможность задавать количество точек из xml, через app:pointCount
        val rangeToFill = when {
            count > points.lastIndex -> points.indices
            else -> 0 until count
        }
        points.indices.forEach {
            points[it] = paintBrush
        }
        rangeToFill.forEach {
            points[it] = paintBrushFilled
        }
        filledPoints = count
        invalidate()
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putInt("filledPoints", filledPoints)
        bundle.putParcelable("superState", super.onSaveInstanceState())
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        var viewState: Parcelable? = state
        if (viewState is Bundle) {
            filledPoints = viewState.getInt("filledPoints", 0)
            viewState = viewState.getParcelable("superState")
        }
        super.onRestoreInstanceState(viewState)
        setRiskPoints(filledPoints)
    }

    override fun onDraw(canvas: Canvas) {
        points.forEach { paint ->
            canvas.drawCircle(
                pointRadius + paddingLeft,
                height.toFloat() / 2,
                pointRadius - paint.strokeWidth / 2,
                paint
            )
            //canvas.drawRect(0f, 0f, 70f,70f, paint)
            canvas.translate(pointRadius * 2 + pointsDistance, 0f)
        }
        canvas.drawText("Hello it is new text", 30f, height - textHeight, textPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = pointsCount * (pointRadius * 2 + pointsDistance) - pointsDistance
        val desiredHeight = pointRadius * 2 /*+ textHeight*/
        setMeasuredDimension(
            resolveSize(desiredWidth.toInt(), widthMeasureSpec) + paddingLeft + paddingRight,
            resolveSize(desiredHeight.toInt(), heightMeasureSpec) + paddingTop + paddingBottom
        )
    }
}