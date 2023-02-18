package com.kozak.pw.domain.utils.markov_ng

/**
 * A procedural name generator that uses Markov chains built from a user-provided array of words.
 */
class PersonNameGeneratorImpl(
    private val dataFirstNames: Set<String>,
    private val dataLastNames: Set<String>
) : NameGenerator {

    /**
     * @return "$firstName $lastName" of a Person
     */
    override fun generate(lengthRange: IntRange): String {
        val firstName = NameGeneratorImpl(dataFirstNames).generate(lengthRange)
        val lastName = NameGeneratorImpl(dataLastNames).generate(lengthRange)
        return "$firstName $lastName"
    }
}
