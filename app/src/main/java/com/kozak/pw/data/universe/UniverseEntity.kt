package com.kozak.pw.data.universe

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kozak.pw.data.PwAnyEntity
import com.kozak.pw.data.TablesNamesConstants

@Entity(tableName = TablesNamesConstants.UNIVERSE_ENTITY_TABLE_NAME)
data class UniverseEntity(
    @PrimaryKey
    override val id: Long = 1,
    override var name: String,
    override val createdAt: String,
    override var health: Int,
    override var mass: Long
) : PwAnyEntity()