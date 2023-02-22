package com.kozak.pw.domain.space

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.Size
import com.kozak.pw.domain.person.Person
import com.kozak.pw.domain.planet.Animal
import com.kozak.pw.domain.planet.Country
import com.kozak.pw.domain.planet.Plant
import com.kozak.pw.domain.planet.TerritoryHexagon

class Planet(
    mass: Long,
    size: Size,
    val starId: Long,
    val acidity: Int,
    val waterPercent: Int,
    val averageTemperature: Int,
    val oxygenPercent: Int
) : HeavenlyBody(mass, size) {

    companion object {
        val PLANET_MASS_RANGE = 100_000_000L..999_999_999L
        val PLANETS_SIZE_RANGE = 1..5

        val INITIAL_ACIDITY_RANGE = 0..1000
        val INITIAL_WATER_PERCENT_RANGE = 0..100
        val INITIAL_AVERAGE_TEMPERATURE_RANGE = -273..10_000
        val INITIAL_OXYGEN_PERCENT_RANGE = 0..80

        val ACIDITY_FOR_LIFE = 0..300
        val WATER_PERCENT_FOR_LIFE = 20..80
        val AVERAGE_TEMPERATURE_FOR_LIFE = -30..60
        val OXYGEN_PERCENT_FOR_LIFE = 10..60
    }

    val countries = mutableListOf<Country>()
    val persons = mutableListOf<Person>()
    val animals = mutableListOf<Animal>()
    val plants = mutableListOf<Plant>()
    val territoryHexagons = mutableListOf<List<TerritoryHexagon>>()
    val landPercent = PwConstants.PERCENTS_100 - waterPercent

    fun isFitForLife(): Boolean {
        return acidity in ACIDITY_FOR_LIFE &&
                waterPercent in WATER_PERCENT_FOR_LIFE &&
                averageTemperature in AVERAGE_TEMPERATURE_FOR_LIFE &&
                oxygenPercent in OXYGEN_PERCENT_FOR_LIFE
    }
}
