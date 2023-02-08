package com.kozak.pw.data.game

import androidx.room.Dao
import androidx.room.Query
import com.kozak.pw.domain.game.PwGameEntity

@Dao
interface PwGameDao {

    @Query("SELECT * FROM game LIMIT 1")
    suspend fun getGameInfo(): PwGameEntity?

    @Query("DELETE FROM game")
    suspend fun deleteGamesInfo()
}