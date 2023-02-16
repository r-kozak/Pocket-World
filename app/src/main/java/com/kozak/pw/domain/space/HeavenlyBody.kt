package com.kozak.pw.domain.space

import com.kozak.pw.domain.PwAny

open class HeavenlyBody(mass: Long) : PwAny(mass = mass) {

    override fun calculateMass(): Long {
        return mass
    }
}
