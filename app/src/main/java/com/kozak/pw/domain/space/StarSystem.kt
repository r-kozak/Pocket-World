package com.kozak.pw.domain.space

import com.kozak.pw.domain.Size

class StarSystem(name: String, size: Size) : HeavenlyBody(name) {

    val stars = mutableListOf<HeavenlyBodyResource>()

    val planets = listOf<Planet>()

    val size = size
}