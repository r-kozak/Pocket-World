package com.kozak.pw.domain.planet

import com.kozak.pw.domain.Coordinate
import com.kozak.pw.domain.space.HeavenlyBodyResource

class TerritoryHexagon(val coordinate: Coordinate, var owner: Country? = null) {

    val resources = mutableListOf<HeavenlyBodyResource>()
}