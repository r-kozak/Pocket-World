package com.kozak.pw.domain.god

import com.kozak.pw.domain.PwAny
import com.kozak.pw.domain.Size

class DarkMatter : PwAny() {

    override val nameLengthRange: IntRange = IntRange(25, 100)

    val hell = Hell()

    override fun calculateMass(): Long {
        TODO("Not yet implemented")
    }

    override fun calculateSize(): Size {
        TODO("Not yet implemented")
    }
}