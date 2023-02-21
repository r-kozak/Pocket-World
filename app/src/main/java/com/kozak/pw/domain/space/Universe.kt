package com.kozak.pw.domain.space

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.PwAny
import com.kozak.pw.domain.Size

class Universe : PwAny() {

    companion object {
        val INITIAL_HEALTH: IntRange = IntRange(80, 120)
    }

    override val nameLengthRange: IntRange = IntRange(6, 12)

    private val galaxies = mutableListOf<Galaxy>()

    override fun calculateMass(): Long {
        return galaxies.sumOf { mass }
    }

    override fun calculateSize(): Size {
        return Size(
            galaxies.sumOf { size?.width ?: 0 } * PwConstants.INTERSTELLAR_SPACE_COEFFICIENT,
            galaxies.sumOf { size?.height ?: 0 } * PwConstants.INTERSTELLAR_SPACE_COEFFICIENT
        )
    }
}