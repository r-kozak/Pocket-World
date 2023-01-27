package com.kozak.pw.data.person

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonItemDao {

    @Query("SELECT * FROM person_items")
    fun getPersonsList(): LiveData<List<PersonItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrUpdatePerson(personItemEntity: PersonItemEntity)

    @Query("DELETE FROM person_items WHERE id=:personItemId")
    suspend fun deletePerson(personItemId: Long)

    @Query("SELECT * FROM person_items WHERE id=:personId LIMIT 1")
    suspend fun getPersonById(personId: Long): PersonItemEntity
}