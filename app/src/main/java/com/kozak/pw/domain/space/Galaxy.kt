package com.kozak.pw.domain.space

import com.kozak.pw.domain.PwAny

class Galaxy(val universeId: Long) : PwAny() {

    val starSystems = mutableListOf<StarSystem>()

    override fun calculateMass(): Long {
        return starSystems.sumOf { mass }
    }
}