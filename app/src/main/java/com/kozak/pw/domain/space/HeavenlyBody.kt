package com.kozak.pw.domain.space

import com.kozak.pw.domain.PwAny

open class HeavenlyBody : PwAny() {

    override fun calculateMass(): Long {
        return mass
    }
}
