package com.kozak.pw.data.galaxy

import androidx.room.Dao
import androidx.room.RoomDatabase
import com.kozak.pw.data.BaseDao
import com.kozak.pw.data.TablesNamesConstants

@Dao
abstract class GalaxyDao(roomDatabase: RoomDatabase) :
    BaseDao<GalaxyEntity>(TablesNamesConstants.GALAXY_ENTITY_TABLE_NAME, roomDatabase)