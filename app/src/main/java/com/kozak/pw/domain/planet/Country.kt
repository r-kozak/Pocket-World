package com.kozak.pw.domain.planet

import com.kozak.pw.domain.person.Person

class Country(val name: String) {

    val citizens = mutableListOf<Person>()

    val countryResource = mutableListOf<CountryResource>()
}