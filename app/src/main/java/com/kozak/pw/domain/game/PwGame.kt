package com.kozak.pw.domain.game

import kotlinx.datetime.LocalDateTime


data class PwGame(
    val id: Int,
    val createdAt: LocalDateTime,
    val gameSpeed: GameSpeed
)