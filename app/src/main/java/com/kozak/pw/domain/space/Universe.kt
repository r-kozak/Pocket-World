package com.kozak.pw.domain.space

import com.kozak.pw.domain.PwAny

class Universe : PwAny() {

    override val nameLengthRange: IntRange = IntRange(6, 12)
    val galaxies = mutableListOf<Galaxy>()
}