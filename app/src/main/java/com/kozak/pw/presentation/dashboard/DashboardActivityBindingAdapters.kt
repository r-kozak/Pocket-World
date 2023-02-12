package com.kozak.pw.presentation.dashboard

import android.view.View
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter

@BindingAdapter("mainDataVisibility")
fun bindMainDataVisibility(constraintLayout: ConstraintLayout, isLoading: Boolean) {
    if (isLoading) constraintLayout.visibility = View.GONE
    else constraintLayout.visibility = View.VISIBLE
}

@BindingAdapter("stateRefreshingProgressVisibility")
fun bindStateRefreshingProgressVisibility(relativeLayout: RelativeLayout, isLoading: Boolean) {
    if (isLoading) relativeLayout.visibility = View.VISIBLE
    else relativeLayout.visibility = View.GONE
}