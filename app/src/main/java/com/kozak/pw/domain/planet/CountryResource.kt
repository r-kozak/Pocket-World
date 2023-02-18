package com.kozak.pw.domain.planet

import com.kozak.pw.domain.Coordinate
import com.kozak.pw.domain.PwAny

class CountryResource(
    val type: CountryResourceType,
    val coordinate: Coordinate
) : PwAny() {

    override fun calculateMass(): Long {
        TODO("Not yet implemented")
    }
}