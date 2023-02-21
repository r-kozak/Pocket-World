package com.kozak.pw.data.star

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kozak.pw.data.PwAnyEntity
import com.kozak.pw.data.TablesNamesConstants
import com.kozak.pw.data.star_system.StarSystemEntity

@Entity(
    tableName = TablesNamesConstants.STAR_ENTITY_TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = StarSystemEntity::class, childColumns = arrayOf("starSystemId"),
        parentColumns = arrayOf("id"), onDelete = ForeignKey.CASCADE
    )]
)
data class StarEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long,
    override val createdAt: String,
    override val name: String,
    override val health: Int,
    override val mass: Long,
    override val sizeWidth: Int,
    override val sizeHeight: Int,
    // ForeignKey of StarSystem to set relation
    val starSystemId: Long
) : PwAnyEntity()