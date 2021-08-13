package com.custom.views_library

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.custom.views_library.utils.toDp

class LoadingButton
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    var buttonText: String
        get() = textView.text.toString()
        set(value) {
            textView.text = value
        }

    var isButtonLoading = false
        set(value) {
            if (value) startLoading()
            else finishLoading()
            field = value
        }

    var loaderDrawable: Drawable? = null
        get() = progressBar.indeterminateDrawable
        set(value) {
            progressBar.indeterminateDrawable = value
            field = value
        }
    private var buttonTextAppearance = 0
    private var progressBar: ProgressBar = ProgressBar(context)
    private var textView = TextView(context)
    private var progressBarSize: Int = 120

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingButton)
            typedArray.getDrawable(R.styleable.LoadingButton_buttonLoader)?.let {
                loaderDrawable = it
                progressBar.indeterminateDrawable = loaderDrawable
            }
            typedArray.getString(R.styleable.LoadingButton_buttonText)?.let {
                buttonText = it
            }
            buttonTextAppearance = typedArray.getInt(R.styleable.LoadingButton_buttonTextAppearance, R.style.TitleStyle)
            progressBarSize = typedArray.getDimensionPixelSize(R.styleable.LoadingButton_buttonLoaderSize, 120)
            isButtonLoading = typedArray.getBoolean(R.styleable.LoadingButton_buttonLoading, false)
            typedArray.recycle()
        }
        textView.text = buttonText
        textView.setTextAppearance(buttonTextAppearance)
        addView(
            progressBar,
            LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT,
            ).apply {
                gravity = Gravity.CENTER
                width = progressBarSize.toDp
                height = progressBarSize.toDp
            }
        )
        addView(
            textView,
            LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT,
            ).apply {
                gravity = Gravity.CENTER
            }
        )
    }

    private fun finishLoading() {
        progressBar.visibility = View.GONE
        textView.visibility = View.VISIBLE
    }

    private fun startLoading() {
        progressBar.visibility = View.VISIBLE
        textView.visibility = View.GONE
    }

    fun setLoaderClickListener(callBack: () -> Unit) {
        this.setOnClickListener {
            if (!isButtonLoading) {
                this.isButtonLoading = true
            }
            callBack()
        }
    }
}