package com.kozak.pw.domain.game

interface PwGameRepository {

    fun getGameInfo(): PwGameEntity?
}