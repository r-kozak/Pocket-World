package com.kozak.pw.domain.planet

import com.kozak.pw.domain.Coordinate

class TerritoryHexagon(coordinate: Coordinate) {

    var owner: Country? = null

    val coordinate: Coordinate = coordinate
}