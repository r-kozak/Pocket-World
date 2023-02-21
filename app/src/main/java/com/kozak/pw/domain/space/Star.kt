package com.kozak.pw.domain.space

import com.kozak.pw.domain.Size

class Star(mass: Long, size: Size, val starSystemId: Long) : HeavenlyBody(mass, size) {

    val resources = mutableListOf<HeavenlyBodyResource>()
    val temperature: Int = 0
}