package com.kozak.pw.data.person

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kozak.pw.data.PwAnyEntity
import com.kozak.pw.data.TablesNamesConstants
import com.kozak.pw.domain.planet.Animal

@Entity(tableName = TablesNamesConstants.PERSON_ENTITY_TABLE_NAME)
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    override var id: Long,
    override val createdAt: String,
    override var name: String,
    override var mass: Long,
    override var health: Int,
    val birthDate: String,
    val sex: Animal.Sex,
    var strength: Int,
    var isFavorite: Boolean,
    var isAlive: Boolean,
    var deathDate: String? = null,
) : PwAnyEntity()