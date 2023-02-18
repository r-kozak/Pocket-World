package com.kozak.pw.domain.space

import com.kozak.pw.domain.Size

class StarSystem(mass: Long, val size: Size) : HeavenlyBody() {

    val stars = mutableListOf<Star>()
    val planets = listOf<Planet>()

    override fun calculateMass(): Long {
        return planets.sumOf { mass } + stars.sumOf { mass }
    }
}