package com.custom.views_library

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ProgressBar

@SuppressLint("CustomViewStyleable")
class FullScreenProgressBar
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    var progressBar: ProgressBar = ProgressBar(context)

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FullScreenProgressBar)
            progressBar.indeterminateDrawable = typedArray.getDrawable(R.styleable.FullScreenProgressBar_customLoader)
            typedArray.recycle()
        }

        addView(
            progressBar,
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER)
        )
        setBackgroundColor(Color.parseColor("#FFFFFF"))
    }
}