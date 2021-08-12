package com.custom.views_library

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.custom.views_library.utils.getPositionType

class TitledTextView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var titleView = TextView(context)
    private var textView = TextView(context)
    private var titlePosition = 0
    private var textPosition = 0
    private var titleStyle = 0
    private var textStyle = 0
    private var verticalOffset = 0

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitledTextView)
            verticalOffset = typedArray.getDimensionPixelOffset(R.styleable.TitledTextView_verticalOffset, 0)
            titlePosition = typedArray.getInt(R.styleable.TitledTextView_titlePosition, 0)
            textPosition = typedArray.getInt(R.styleable.TitledTextView_textPosition, 0)
            titleView.text = typedArray.getString(R.styleable.TitledTextView_title)
            textView.text = typedArray.getString(R.styleable.TitledTextView_text)
            titleStyle = typedArray.getInt(R.styleable.TitledTextView_titleAppearance, R.style.TitleStyle)
            textStyle = typedArray.getInt(R.styleable.TitledTextView_textAppearance, R.style.TextStyle)
            titleView.setTextAppearance(titleStyle)
            textView.setTextAppearance(textStyle)
            typedArray.recycle()
        }
        orientation = VERTICAL

        addView(
            titleView, LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).apply { gravity = titlePosition.getPositionType() }
        )
        addView(
            textView,
            LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = textPosition.getPositionType()
                setMargins(0, verticalOffset, 0, 0)
            }
        )
    }

    var text: String
        get() = textView.text.toString()
        set(value) {
            textView.text = value
        }

    var title: String
        get() = titleView.text.toString()
        set(value) {
            titleView.text = value
            titleView.isVisible = value.isNotEmpty()
        }
}