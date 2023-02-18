package com.kozak.pw

import com.kozak.pw.domain.utils.markov_ng.NameGenerator
import com.kozak.pw.domain.utils.markov_ng.NameGeneratorImpl
import org.junit.Assert
import org.junit.Test
import java.io.File

class NameGeneratorTest {

    private val names: Set<String> = File("src/test/resources/names.txt").readLines().toSet()

    private val generator: NameGenerator = NameGeneratorImpl(names, 2, 0.001f)

    private val nameLengthRange = 3..10

    @Test
    fun testGeneration() {
        val name1 = generator.generate(nameLengthRange)
        val name2 = generator.generate(nameLengthRange)
        println("Generated name1 = $name1")
        println("Generated name2 = $name2")

        Assert.assertNotNull(name1)
        Assert.assertNotEquals("", name1)

        Assert.assertNotSame(name1, name2)
    }

    @Test
    fun testGeneratedLength() {
        val iterationsCount = 10_000
        val generatedNames = mutableListOf<String>()
        repeat (iterationsCount) {
            generatedNames += generator.generate(nameLengthRange)
        }
        assert(generatedNames.size == iterationsCount)

        generatedNames.sortBy { it.length }
        Assert.assertEquals(nameLengthRange.first, generatedNames.first().length)
        Assert.assertEquals(nameLengthRange.last, generatedNames.last().length)
    }

}