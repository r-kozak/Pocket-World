package com.kozak.pw.domain.space

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.PwAny
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class Universe(
    health: Int,
    mass: Long = 0,
    id: Long = PwConstants.DEFAULT_ITEM_ID,
    createdAt: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    name: String = PwConstants.DEFAULT_ITEM_NAME,
) : PwAny(id = id, createdAt = createdAt, name = name, health = health, mass = mass) {

    companion object {
        val INITIAL_HEALTH: IntRange = IntRange(80, 120)
    }

    override val nameLengthRange: IntRange = IntRange(6, 12)

    val galaxies = mutableListOf<Galaxy>()

    override fun calculateMass(): Long {
        return galaxies.sumOf { mass }
    }
}