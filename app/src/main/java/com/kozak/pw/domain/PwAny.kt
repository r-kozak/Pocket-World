package com.kozak.pw.domain

import android.util.Log
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.utils.markov_ng.MarkovGenerator

abstract class PwAny(
    var mass: Long = 0,
    var health: Int = 0,
    name: String = "",
    var id: Long = PwConstants.DEFAULT_ITEM_ID,
) {
    open val nameLengthRange: IntRange = IntRange(NAME_DEFAULT_LENGTH_FROM, NAME_DEFAULT_LENGTH_TO)

    var name: String = name
        get() {
            if (field.isBlank()) field = generateName()
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
