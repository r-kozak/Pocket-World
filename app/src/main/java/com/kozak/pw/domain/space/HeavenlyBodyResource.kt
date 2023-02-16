package com.kozak.pw.domain.space

import com.kozak.pw.domain.Coordinate
import com.kozak.pw.domain.PwAny

class HeavenlyBodyResource(
    val type: HeavenlyBodyResourceType,
    val coordinate: Coordinate,
    mass: Long,
    health: Int
) : PwAny(mass = mass, health = health) {

    override fun calculateMass(): Long {
        return mass
    }
}