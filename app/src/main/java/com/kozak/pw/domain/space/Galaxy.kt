package com.kozak.pw.domain.space

class Galaxy(name: String) : HeavenlyBody(name) {

    val starSystems = mutableListOf<StarSystem>()

}