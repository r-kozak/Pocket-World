package com.kozak.pw.domain.space

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.PwAny
import com.kozak.pw.domain.Size

class Galaxy(val universeId: Long) : PwAny() {

    val starSystems = mutableListOf<StarSystem>()

    override fun calculateMass(): Long {
        return starSystems.sumOf { mass }
    }

    override fun calculateSize(): Size {
        return Size(
            starSystems.sumOf { size?.width ?: 0 } * PwConstants.INTERSTELLAR_SPACE_COEFFICIENT,
            starSystems.sumOf { size?.height ?: 0 } * PwConstants.INTERSTELLAR_SPACE_COEFFICIENT
        )
    }
}