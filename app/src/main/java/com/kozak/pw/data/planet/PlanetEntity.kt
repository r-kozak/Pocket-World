package com.kozak.pw.data.planet

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kozak.pw.data.PwAnyEntity
import com.kozak.pw.data.TablesNamesConstants
import com.kozak.pw.data.star.StarEntity

@Entity(
    tableName = TablesNamesConstants.PLANET_ENTITY_TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = StarEntity::class, childColumns = arrayOf("starId"),
        parentColumns = arrayOf("id"), onDelete = ForeignKey.CASCADE
    )]
)
data class PlanetEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long,
    override val createdAt: String,
    override val name: String,
    override val health: Int,
    override val mass: Long,
    // ForeignKey of Star to set relation
    val starId: Long,
    override val sizeWidth: Int,
    override val sizeHeight: Int
) : PwAnyEntity()