package com.kozak.pw.domain

import android.util.Log
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.utils.markov_ng.MarkovGenerator
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

abstract class PwAny(
    var id: Long = PwConstants.DEFAULT_ITEM_ID,
    val createdAt: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    name: String = PwConstants.DEFAULT_ITEM_NAME,
    mass: Long = PwConstants.DEFAULT_ITEM_MASS,
    var health: Int = 0
) {
    open val nameLengthRange: IntRange = IntRange(NAME_DEFAULT_LENGTH_FROM, NAME_DEFAULT_LENGTH_TO)

    var mass = mass
        get() {
            if (field == PwConstants.DEFAULT_ITEM_MASS) return calculateMass()
            return field
        }

    abstract fun calculateMass(): Long

    var name: String = name
        get() {
            if (field == PwConstants.DEFAULT_ITEM_NAME) field = generateName()
            return field
        }

    companion object {
        const val NAME_DEFAULT_LENGTH_FROM = 5
        const val NAME_DEFAULT_LENGTH_TO = 10
    }

    private fun generateName(): String {
        val generatedName = MarkovGenerator.createForClass(this.javaClass).generate(nameLengthRange)
        Log.d(
            PwConstants.LOG_TAG,
            "Generated name for new ${this.javaClass.simpleName} - $generatedName"
        )
        return generatedName
    }
}
