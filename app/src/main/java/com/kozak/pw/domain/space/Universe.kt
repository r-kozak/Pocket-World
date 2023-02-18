package com.kozak.pw.domain.space

import com.kozak.pw.domain.PwAny

class Universe : PwAny() {

    companion object {
        val INITIAL_HEALTH: IntRange = IntRange(80, 120)
    }

    override val nameLengthRange: IntRange = IntRange(6, 12)

    val galaxies = mutableListOf<Galaxy>()

    override fun calculateMass(): Long {
        return galaxies.sumOf { mass }
    }
}