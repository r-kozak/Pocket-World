package com.kozak.pw.domain.space

import com.kozak.pw.domain.Coordinate
import com.kozak.pw.domain.PwAny
import com.kozak.pw.domain.Size

class HeavenlyBodyResource(
    val type: HeavenlyBodyResourceType,
    val coordinate: Coordinate,
    mass: Long,
    size: Size?
) : PwAny(mass, size) {

    override fun calculateMass(): Long {
        return mass
    }

    override fun calculateSize(): Size {
        TODO("Not yet implemented")
    }
}