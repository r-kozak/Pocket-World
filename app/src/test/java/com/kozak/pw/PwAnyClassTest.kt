package com.kozak.pw

import com.kozak.pw.domain.Coordinate
import com.kozak.pw.domain.PwAny
import com.kozak.pw.domain.Size
import com.kozak.pw.domain.planet.CountryResource
import com.kozak.pw.domain.planet.CountryResourceType
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for PwAny descendants objects
 */
class PwAnyClassTest {

    companion object {
        const val PW_ANY_TEST_TEST_PROPERTY = 123
        const val PW_ANY_HEALTH = 100
        const val COORDINATE_X = 149
        const val COORDINATE_Y = 850
        const val COUNTRY_RESOURCE_MASS = 9999L
        const val PW_ANY_MASS = 159L
    }

    class PwAnyTest(override val nameLengthRange: IntRange) : PwAny() {
        var testProperty = PW_ANY_TEST_TEST_PROPERTY
        override fun calculateMass(): Long {
            return PW_ANY_MASS
        }
        override fun calculateSize(): Size {
            return Size(10, 20)
        }
    }

    @Test
    fun pwAnyDescendants() {
        val pwAnyTest = PwAnyTest(IntRange(5, 10))
        pwAnyTest.health = PW_ANY_HEALTH
        pwAnyTest.mass = Long.MAX_VALUE
        pwAnyTest.testProperty = PW_ANY_TEST_TEST_PROPERTY

        assertEquals(PW_ANY_HEALTH, pwAnyTest.health)
        assertEquals(Long.MAX_VALUE, pwAnyTest.mass)
        assertEquals(PW_ANY_TEST_TEST_PROPERTY, pwAnyTest.testProperty)
    }

    @Test
    fun creationOfCountryResourceObject() {
        val countryResource = CountryResource(
            CountryResourceType.FOOD,
            Coordinate(COORDINATE_X, COORDINATE_Y),
            10,
            Size(30, 40)
        )
        assertEquals(CountryResourceType.FOOD.name, countryResource.type.name)
        assertEquals(COORDINATE_X, countryResource.coordinate.x)
        assertEquals(COORDINATE_Y, countryResource.coordinate.y)
        assertEquals(COUNTRY_RESOURCE_MASS, countryResource.mass)
        assertEquals(PW_ANY_HEALTH, countryResource.health)
    }
}