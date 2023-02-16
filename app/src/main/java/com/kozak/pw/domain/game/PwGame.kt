package com.kozak.pw.domain.game

import kotlinx.datetime.LocalDateTime


data class PwGame(
    val createdAt: LocalDateTime,
    val gameSpeed: GameSpeed,
    val id: Long = 1, // always 1 instance
)