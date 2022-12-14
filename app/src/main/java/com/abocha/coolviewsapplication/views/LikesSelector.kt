package com.abocha.coolviewsapplication.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import com.abocha.coolviewsapplication.R

class LikeSelector
@JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr) {
    private lateinit var mThumbUp: ImageView
    private lateinit var mThumbDown: ImageView
    private lateinit var mLikes: TextView
    private var initLikes = 0

    init {
        initializeViews()
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.LikeSelector)
        initLikes = typedArray.getInt(R.styleable.LikeSelector_likesCounter, 0)
        typedArray.recycle()
    }

    private fun initializeViews() {
        inflate(context, R.layout.likeselector_view, this)
        orientation = HORIZONTAL
        background = ContextCompat.getDrawable(context, R.drawable.top_bottom_border)
        setPadding(context.dip2px(16))
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        mThumbUp = findViewById(R.id.thumb_up_selector)
        mThumbDown = findViewById(R.id.thumb_down_selector)
        mLikes = findViewById(R.id.likes_text_view)
        mLikes.text = initLikes.toString()

        mThumbUp.setOnClickListener {
            var likes = Integer.valueOf(mLikes.text.toString())
            likes++
            mLikes.text = likes.toString()
        }
        mThumbDown.setOnClickListener {
            var likes = Integer.valueOf(mLikes.text.toString())
            if (likes == 0) return@setOnClickListener
            likes--
            mLikes.text = likes.toString()
        }
    }
}

fun Context.dip2px(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun Context.sp2px(value: Int): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5f, this.resources.displayMetrics)