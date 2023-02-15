package com.kozak.pw.domain.utils.markov_ng

import com.kozak.pw.PwApp
import com.kozak.pw.R
import com.kozak.pw.domain.PwAny
import com.kozak.pw.domain.space.Universe
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.streams.toList

/**
 * Interface for name generators
 */
interface MarkovGenerator {

    companion object {
        fun createForClass(clazz: Class<PwAny>) =
            when (clazz) {
                Universe::class.java -> MarkovGeneratorImpl(getExampleNames(R.raw.universes_en))
                else -> throw Exception("I don't know how to deal with $clazz.")
            }

        private fun getExampleNames(rRawId: Int): Set<String> {
            val inStream = PwApp.getInstance().applicationContext.resources.openRawResource(rRawId)
            return BufferedReader(InputStreamReader(inStream)).lines().toList().toSet()
        }
    }

    /**
     * Generates new string
     * @param lengthRange bounds of length of generated String
     */
    fun generate(lengthRange: IntRange): String
}
