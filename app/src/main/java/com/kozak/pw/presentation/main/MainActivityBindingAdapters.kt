package com.kozak.pw.presentation.main

import android.view.View
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter

@BindingAdapter("mainDataVisibility")
fun bindMainDataVisibility(constraintLayout: ConstraintLayout, isStateRefreshed: Boolean) {
    if (isStateRefreshed) constraintLayout.visibility = View.VISIBLE
    else constraintLayout.visibility = View.GONE
}

@BindingAdapter("stateRefreshingProgressVisibility")
fun bindStateRefreshingProgressVisibility(relativeLayout: RelativeLayout, isStateRefreshed: Boolean) {
    if (isStateRefreshed) relativeLayout.visibility = View.GONE
    else relativeLayout.visibility = View.VISIBLE
}