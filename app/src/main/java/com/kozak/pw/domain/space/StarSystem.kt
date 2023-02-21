package com.kozak.pw.domain.space

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.PwAny
import com.kozak.pw.domain.Size

class StarSystem(val galaxyId: Long) : PwAny() {

    val stars = mutableListOf<Star>()
    val planets = mutableListOf<Planet>()

    override fun calculateMass(): Long {
        return planets.sumOf { mass } + stars.sumOf { mass }
    }

    override fun calculateSize(): Size {
        val width = (stars.sumOf { size?.width ?: 0 } +
                planets.sumOf { size?.width ?: 0 }) * PwConstants.INTERSTELLAR_SPACE_COEFFICIENT

        val height = (stars.sumOf { size?.height ?: 0 } +
                planets.sumOf { size?.height ?: 0 }) * PwConstants.INTERSTELLAR_SPACE_COEFFICIENT

        return Size(width, height)
    }
}