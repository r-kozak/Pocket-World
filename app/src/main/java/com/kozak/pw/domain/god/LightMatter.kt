package com.kozak.pw.domain.god

import com.kozak.pw.domain.PwAny
import com.kozak.pw.domain.Size
import com.kozak.pw.domain.space.Universe

class LightMatter : PwAny() {

    val universes = mutableListOf<Universe>()

    override fun calculateMass(): Long {
        TODO("Not yet implemented")
    }

    override fun calculateSize(): Size {
        TODO("Not yet implemented")
    }
}