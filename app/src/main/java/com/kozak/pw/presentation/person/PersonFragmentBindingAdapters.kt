package com.kozak.pw.presentation.person

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.kozak.pw.R
import com.kozak.pw.short
import kotlinx.datetime.LocalDateTime

@BindingAdapter("localDateTimeToShort")
fun bindLocalDateTime(textView: TextView, dateTime: LocalDateTime?) {
    textView.text = dateTime.short()
}

@BindingAdapter("errorInputFirstName")
fun bindErrorInputFirstName(til: TextInputLayout, isError: Boolean) {
    til.error = if (isError) til.context.getString(R.string.error_input_first_name) else null
}

@BindingAdapter("errorInputLastName")
fun bindErrorInputLastName(til: TextInputLayout, isError: Boolean) {
    til.error = if (isError) til.context.getString(R.string.error_input_last_name) else null
}

@BindingAdapter("errorInputStrength")
fun bindErrorInputStrength(til: TextInputLayout, isError: Boolean) {
    til.error = if (isError) til.context.getString(R.string.error_input_strength) else null
}