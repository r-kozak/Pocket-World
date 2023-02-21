package com.kozak.pw.domain.space

import com.kozak.pw.domain.Size

class Star(mass: Long, override var size: Size, val starSystemId: Long) : HeavenlyBody() {

    val resources = mutableListOf<HeavenlyBodyResource>()
    val temperature: Int = 0
}