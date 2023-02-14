package com.kozak.pw.domain.game

import com.kozak.pw.domain.BaseRepository

interface PwGameRepository : BaseRepository<PwGame> {

    suspend fun getGameInfo(): PwGame?

    suspend fun gameInstanceExists() = getGameInfo() != null

    suspend fun destroyCurrentWorld()
}