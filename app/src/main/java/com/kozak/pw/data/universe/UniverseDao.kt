package com.kozak.pw.data.universe

import androidx.room.Dao
import androidx.room.RoomDatabase
import com.kozak.pw.data.BaseDao
import com.kozak.pw.data.TablesNamesConstants

@Dao
abstract class UniverseDao(roomDatabase: RoomDatabase) :
    BaseDao<UniverseEntity>(TablesNamesConstants.UNIVERSE_ENTITY_TABLE_NAME, roomDatabase)