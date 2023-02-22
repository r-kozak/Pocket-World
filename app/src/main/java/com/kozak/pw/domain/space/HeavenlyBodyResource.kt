package com.kozak.pw.domain.space

import com.kozak.pw.domain.PwAny
import com.kozak.pw.domain.Size

class HeavenlyBodyResource(
    val type: HeavenlyBodyResourceType,
    val territoryHexagonId: Long,
    mass: Long,
    size: Size
) : PwAny(mass, size) {

    override fun calculateMass(): Long {
        return mass
    }

    override fun calculateSize(): Size {
        return size!!
    }
}