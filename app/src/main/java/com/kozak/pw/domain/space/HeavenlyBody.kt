package com.kozak.pw.domain.space

import com.kozak.pw.domain.PwAny
import com.kozak.pw.domain.Size

open class HeavenlyBody : PwAny() {

    open lateinit var size: Size

    override fun calculateMass(): Long {
        return mass
    }
}
