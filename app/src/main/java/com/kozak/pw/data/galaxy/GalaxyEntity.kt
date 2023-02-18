package com.kozak.pw.data.galaxy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kozak.pw.data.PwAnyEntity
import com.kozak.pw.data.TablesNamesConstants

@Entity(tableName = TablesNamesConstants.GALAXY_ENTITY_TABLE_NAME)
data class GalaxyEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long,
    override val createdAt: String,
    override var name: String,
    override var health: Int,
    override var mass: Long,
    // TODO add ForeignKey param to Entity annotation to implement relations
    val universeId: Long
) : PwAnyEntity()