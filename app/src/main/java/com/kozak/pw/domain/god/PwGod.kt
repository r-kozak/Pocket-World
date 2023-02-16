package com.kozak.pw.domain.god

import com.kozak.pw.domain.PwAny

class PwGod : PwAny() {

    val lightMatter = LightMatter()
    val darkMatter = DarkMatter()

    override fun calculateMass(): Long {
        TODO("Not yet implemented")
    }
}