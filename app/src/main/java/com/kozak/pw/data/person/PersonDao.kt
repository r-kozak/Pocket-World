package com.kozak.pw.data.person

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonDao {

    @Query("SELECT * FROM persons WHERE isAlive")
    fun getAlivePersonsList(): LiveData<List<PersonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrUpdatePerson(personEntity: PersonEntity)

    @Query("DELETE FROM persons WHERE id=:personId")
    suspend fun deletePerson(personId: Long)

    @Query("SELECT * FROM persons WHERE id=:personId LIMIT 1")
    suspend fun getPersonById(personId: Long): PersonEntity
}