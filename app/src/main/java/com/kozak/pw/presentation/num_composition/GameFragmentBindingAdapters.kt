package com.kozak.pw.presentation.num_composition

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

interface OnOptionClickListener {
    fun onOptionClick(int: Int)
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, isEnough: Boolean) {
    textView.setTextColor(getColorByState(textView.context, isEnough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, isEnough: Boolean) {
    val color = getColorByState(progressBar.context, isEnough)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}

private fun getColorByState(context: Context, isEnough: Boolean): Int {
    val colorResId = if (isEnough) android.R.color.holo_green_light
    else android.R.color.holo_red_light

    return ContextCompat.getColor(context, colorResId)
}