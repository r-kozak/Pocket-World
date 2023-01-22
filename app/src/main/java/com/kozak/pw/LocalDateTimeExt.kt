package com.kozak.pw

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun LocalDateTime?.short() = this?.toJavaLocalDateTime()
    ?.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) ?: "-"