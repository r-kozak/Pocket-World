package com.kozak.pw.domain.utils.markov_ng

/**
 * Interface for name generators
 */
interface NameGenerator {

    /**
     * Generates new string
     * @param lengthRange bounds of length of generated String
     */
    fun generate(lengthRange: IntRange): String
}
