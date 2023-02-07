package com.kozak.pw.domain.space

import com.kozak.pw.domain.Size

class Star(name: String, size: Size) : HeavenlyBody(name) {

    val resources = mutableListOf<HeavenlyBodyResource>()

    val size = size

    val temperature = 0

}