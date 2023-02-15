package com.kozak.pw.domain.god

import com.kozak.pw.domain.PwAny

class DarkMatter : PwAny() {

    override val nameLengthRange: IntRange = IntRange(25, 100)
    val hell = Hell()
}