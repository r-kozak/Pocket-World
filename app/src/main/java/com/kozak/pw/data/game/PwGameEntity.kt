package com.kozak.pw.domain.game

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class PwGameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val createdAt: String,
    val gameSpeed: GameSpeed
)