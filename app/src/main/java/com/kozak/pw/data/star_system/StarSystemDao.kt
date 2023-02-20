package com.kozak.pw.data.star_system

import androidx.room.Dao
import androidx.room.RoomDatabase
import com.kozak.pw.data.BaseDao
import com.kozak.pw.data.TablesNamesConstants

@Dao
abstract class StarSystemDao(roomDatabase: RoomDatabase) :
    BaseDao<StarSystemEntity>(TablesNamesConstants.STAR_SYSTEM_ENTITY_TABLE_NAME, roomDatabase)