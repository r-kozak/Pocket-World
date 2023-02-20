package com.kozak.pw.data.universe

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kozak.pw.data.PwAnyEntity
import com.kozak.pw.data.TablesNamesConstants

@Entity(tableName = TablesNamesConstants.UNIVERSE_ENTITY_TABLE_NAME)
data class UniverseEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long,
    override val name: String,
    override val createdAt: String,
    override val health: Int,
    override val mass: Long
) : PwAnyEntity()