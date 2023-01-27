package com.kozak.pw.data.person

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonItemDao {

    @Query("SELECT * FROM person_items")
    fun getPersonItemsList(): LiveData<List<PersonItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPersonItem(personItemEntity: PersonItemEntity)

    @Query("DELETE FROM person_items WHERE id=:personItemId")
    fun deletePersonItem(personItemId: Long)

    @Query("SELECT * FROM person_items WHERE id=:personItemId LIMIT 1")
    fun selectPersonItem(personItemId: Long)
}