package com.kozak.pw.domain.planet

import android.util.Log
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.PwAny
import com.kozak.pw.domain.utils.markov_ng.NameGenerator
import kotlinx.datetime.LocalDateTime

abstract class Animal (
    var birthDate: LocalDateTime,
    val sex: Sex,
    var intelligence: Int = 0,
    var beauty: Int = 0,
    var luck: Int = 0,
    var strength: Int = PwConstants.DEFAULT_ANIMAL_STRENGTH,
    var isAlive: Boolean = true,
    var deathDate: LocalDateTime? = null
) : PwAny() {
    enum class Sex {
        MALE, FEMALE
    }

    override fun generateName(): String {
        val generatedName = NameGenerator.createForClass(this.javaClass, sex).generate(nameLengthRange)
        Log.d(
            PwConstants.LOG_TAG,
            "Generated name for new ${this.javaClass.simpleName} - $generatedName"
        )
        return generatedName
    }
}