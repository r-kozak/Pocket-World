package com.kozak.pw.domain.game

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kozak.pw.data.BaseEntity
import com.kozak.pw.data.TablesNamesConstants

@Entity(tableName = TablesNamesConstants.PW_GAME_ENTITY_TABLE_NAME)
data class PwGameEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long,
    val createdAt: String,
    val gameSpeed: GameSpeed
) : BaseEntity()