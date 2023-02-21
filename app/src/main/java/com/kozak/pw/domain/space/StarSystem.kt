package com.kozak.pw.domain.space

class StarSystem(val galaxyId: Long) : HeavenlyBody() {

    val stars = mutableListOf<Star>()
    val planets = mutableListOf<Planet>()

    override fun calculateMass(): Long {
        return planets.sumOf { mass } + stars.sumOf { mass }
    }
}