package com.kozak.pw.domain.game

interface PwGameRepository {

    suspend fun getGameInfo(): PwGame?
    suspend fun deleteGamesInfo()
    suspend fun addNewPwGame(newPwGame: PwGame)
}