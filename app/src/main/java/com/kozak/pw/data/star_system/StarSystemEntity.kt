package com.kozak.pw.data.star_system

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kozak.pw.data.PwAnyEntity
import com.kozak.pw.data.TablesNamesConstants
import com.kozak.pw.data.galaxy.GalaxyEntity

@Entity(
    tableName = TablesNamesConstants.STAR_SYSTEM_ENTITY_TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = GalaxyEntity::class, childColumns = arrayOf("galaxyId"),
        parentColumns = arrayOf("id"), onDelete = ForeignKey.CASCADE
    )]
)
data class StarSystemEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long,
    override val createdAt: String,
    override val name: String,
    override val health: Int,
    override val mass: Long,
    override val sizeWidth: Int?,
    override val sizeHeight: Int?,
    // ForeignKey of Galaxy to set relation
    val galaxyId: Long
) : PwAnyEntity()