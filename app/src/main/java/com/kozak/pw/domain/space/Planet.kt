package com.kozak.pw.domain.space

import com.kozak.pw.PwConstants
import com.kozak.pw.domain.Size
import com.kozak.pw.domain.person.Person
import com.kozak.pw.domain.planet.Animal
import com.kozak.pw.domain.planet.Country
import com.kozak.pw.domain.planet.Plant

class Planet(mass: Long, val size: Size) : HeavenlyBody() {

    val countries = mutableListOf<Country>()

    val persons = mutableListOf<Person>()

    val animals = mutableListOf<Animal>()

    val plants = mutableListOf<Plant>()

    val territoryHexagons = mutableListOf<List<Plant>>()

    val resources = mutableListOf<HeavenlyBodyResource>()

    val acidity = 0

    val averageTemperature = 0

    val oxygenPercent = 0

    val waterPercent = 0

    val landPercent = PwConstants.PERCENTS_100 - waterPercent

}
