package com.ppolivka.namegenerator

/**
 * Interface for name generators
 */
interface MarkovGenerator {

    /**
     * Generates new string
     * @param lengthRange bounds of length of generated String
     */
    fun generate(lengthRange: IntRange): String
}
