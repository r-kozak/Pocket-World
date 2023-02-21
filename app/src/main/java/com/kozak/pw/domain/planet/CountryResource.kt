package com.kozak.pw.domain.planet

import com.kozak.pw.domain.Coordinate
import com.kozak.pw.domain.PwAny
import com.kozak.pw.domain.Size

class CountryResource(
    val type: CountryResourceType,
    val coordinate: Coordinate,
    mass: Long,
    size: Size
) : PwAny(mass, size) {

    override fun calculateMass(): Long {
        TODO("Not yet implemented")
    }

    override fun calculateSize(): Size {
        TODO("Not yet implemented")
    }
}