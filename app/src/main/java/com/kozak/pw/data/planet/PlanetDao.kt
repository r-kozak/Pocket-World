package com.kozak.pw.data.planet

import androidx.room.Dao
import androidx.room.RoomDatabase
import com.kozak.pw.data.BaseDao
import com.kozak.pw.data.TablesNamesConstants

@Dao
abstract class PlanetDao(roomDatabase: RoomDatabase) :
    BaseDao<PlanetEntity>(TablesNamesConstants.PLANET_ENTITY_TABLE_NAME, roomDatabase)