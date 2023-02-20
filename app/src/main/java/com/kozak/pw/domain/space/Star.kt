package com.kozak.pw.domain.space

import com.kozak.pw.domain.Size

class Star(mass: Long, val size: Size, val starSystemId: Long) : HeavenlyBody() {

    val resources = mutableListOf<HeavenlyBodyResource>()
    val temperature: Int = 0
}