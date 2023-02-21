package com.kozak.pw.domain

import android.util.Log
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.utils.markov_ng.NameGeneratorFactory
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

abstract class PwAny(mass: Long = PwConstants.DEFAULT_ITEM_MASS, size: Size? = null) {

    open val nameLengthRange: IntRange = IntRange(NAME_DEFAULT_LENGTH_FROM, NAME_DEFAULT_LENGTH_TO)
    var id: Long = PwConstants.DEFAULT_ITEM_ID
    var createdAt: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    var health: Int = 0

    var mass = mass
        get() {
            if (field == PwConstants.DEFAULT_ITEM_MASS) return calculateMass()
            return field
        }

    var size: Size? = size
        get() {
            if (field == null) return calculateSize()
            return field
        }

    open var name: String = PwConstants.DEFAULT_ITEM_NAME
        get() {
            Log.d(PwConstants.LOG_TAG, "Current person name: $field")
            if (field == PwConstants.DEFAULT_ITEM_NAME) field = generateName()
            return field
        }

    companion object {
        const val NAME_DEFAULT_LENGTH_FROM = 5
        const val NAME_DEFAULT_LENGTH_TO = 10
    }

    abstract fun calculateMass(): Long
    abstract fun calculateSize(): Size

    protected open fun generateName(): String {
        val generatedName =
            NameGeneratorFactory.createForClass(this.javaClass).generate(nameLengthRange)
        Log.d(
            PwConstants.LOG_TAG,
            "Generated name for new ${this.javaClass.simpleName} - $generatedName"
        )
        return generatedName
    }
}
