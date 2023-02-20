package com.kozak.pw.data.star

import androidx.room.Dao
import androidx.room.RoomDatabase
import com.kozak.pw.data.BaseDao
import com.kozak.pw.data.TablesNamesConstants

@Dao
abstract class StarDao(roomDatabase: RoomDatabase) :
    BaseDao<StarEntity>(TablesNamesConstants.STAR_ENTITY_TABLE_NAME, roomDatabase)