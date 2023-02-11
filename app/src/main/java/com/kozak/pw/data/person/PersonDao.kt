package com.kozak.pw.data.person

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kozak.pw.data.BaseDao
import com.kozak.pw.data.TablesNamesConstants

@Dao
abstract class PersonDao(roomDatabase: RoomDatabase) :
    BaseDao<PersonEntity>(TablesNamesConstants.PERSON_ENTITY_TABLE_NAME, roomDatabase) {

    @Query("SELECT * FROM persons WHERE isAlive")
    abstract fun getAlivePersonsList(): LiveData<List<PersonEntity>>
}