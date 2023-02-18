package com.kozak.pw.domain.utils.markov_ng

import com.kozak.pw.PwApp
import com.kozak.pw.R
import com.kozak.pw.domain.PwAny
import com.kozak.pw.domain.person.Person
import com.kozak.pw.domain.planet.Animal
import com.kozak.pw.domain.space.Galaxy
import com.kozak.pw.domain.space.Universe
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.streams.toList

/**
 * Interface for name generators
 */
interface NameGenerator {

    companion object {
        private const val UA_LANGUAGE = "uk"

        fun createForClass(clazz: Class<PwAny>) =
            when (clazz) {
                Universe::class.java -> NameGeneratorImpl(getExampleNames(R.raw.universes_en))
                Galaxy::class.java -> NameGeneratorImpl(getExampleNames(R.raw.roman_places))
                else -> throw Exception("I don't know how to deal with $clazz.")
            }

        fun createForClass(clazz: Class<Animal>, sex: Animal.Sex) =
            when (clazz) {
                Person::class.java -> {
                    val firstNames = getExampleFirstNames(sex)
                    val lastNames = getExampleLastNames(sex)
                    PersonNameGeneratorImpl(firstNames, lastNames)
                }
                else -> throw Exception("I don't know how to deal with $clazz.")
            }

        private fun getExampleFirstNames(sex: Animal.Sex): Set<String> {
            val language = Locale.getDefault().language
            return if (language == Locale(UA_LANGUAGE).language) {
                when (sex) {
                    Animal.Sex.MALE -> getExampleNames(R.raw.man_first_names_ua)
                    Animal.Sex.FEMALE -> getExampleNames(R.raw.woman_first_names_ua)
                }
            } else {
                when (sex) {
                    Animal.Sex.MALE -> getExampleNames(R.raw.man_first_names_en)
                    Animal.Sex.FEMALE -> getExampleNames(R.raw.woman_first_names_en)
                }
            }
        }

        private fun getExampleLastNames(sex: Animal.Sex): Set<String> {
            val language = Locale.getDefault().language
            return if (language == Locale(UA_LANGUAGE).language) {
                when (sex) {
                    Animal.Sex.MALE -> getExampleNames(R.raw.man_surnames_ua)
                    Animal.Sex.FEMALE -> getExampleNames(R.raw.woman_surnames_ua)
                }
            } else {
                when (sex) {
                    Animal.Sex.MALE -> getExampleNames(R.raw.people_surnames_en)
                    Animal.Sex.FEMALE -> getExampleNames(R.raw.people_surnames_en)
                }
            }
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
