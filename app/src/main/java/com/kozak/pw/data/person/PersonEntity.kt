package com.kozak.pw.data.person

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kozak.pw.data.PwAnyEntity
import com.kozak.pw.data.TablesNamesConstants
import com.kozak.pw.domain.planet.Animal

@Entity(tableName = TablesNamesConstants.PERSON_ENTITY_TABLE_NAME)
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long,
    override val createdAt: String,
    override val name: String,
    override val mass: Long,
    override val health: Int,
    val birthDate: String,
    val sex: Animal.Sex,
    val strength: Int,
    val isFavorite: Boolean,
    val isAlive: Boolean,
    val deathDate: String? = null,
) : PwAnyEntity()