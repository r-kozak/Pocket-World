package com.kozak.pw.data.galaxy

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.kozak.pw.data.PwAnyEntity
import com.kozak.pw.data.TablesNamesConstants
import com.kozak.pw.data.universe.UniverseEntity

@Entity(
    tableName = TablesNamesConstants.GALAXY_ENTITY_TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = UniverseEntity::class, childColumns = arrayOf("universeId"),
        parentColumns = arrayOf("id"), onDelete = CASCADE
    )]
)
data class GalaxyEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long,
    override val createdAt: String,
    override val name: String,
    override val health: Int,
    override val mass: Long,
    // ForeignKey of Universe to set relation
    val universeId: Long,
    override val sizeWidth: Int?,
    override val sizeHeight: Int?
) : PwAnyEntity()