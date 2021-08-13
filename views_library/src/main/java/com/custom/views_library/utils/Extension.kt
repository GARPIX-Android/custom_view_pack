package com.custom.views_library.utils

import android.content.res.Resources
import android.view.Gravity
import com.custom.views_library.utils.Constant.CENTER
import com.custom.views_library.utils.Constant.END
import com.custom.views_library.utils.Constant.START

fun Int.getPositionType() = when (this) {
    START -> Gravity.START
    CENTER -> Gravity.CENTER
    END -> Gravity.END
    else -> Gravity.START
}

val Int.toDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()