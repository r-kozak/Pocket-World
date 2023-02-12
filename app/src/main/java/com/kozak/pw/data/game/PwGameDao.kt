package com.kozak.pw.data.game

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RoomDatabase
import com.kozak.pw.data.BaseDao
import com.kozak.pw.data.TablesNamesConstants
import com.kozak.pw.domain.game.PwGameEntity

@Dao
abstract class PwGameDao(roomDatabase: RoomDatabase) :
    BaseDao<PwGameEntity>(TablesNamesConstants.PW_GAME_ENTITY_TABLE_NAME, roomDatabase) {

    @Query("SELECT * FROM game LIMIT 1")
    abstract suspend fun getGameInfo(): PwGameEntity?
}