package com.kozak.pw.domain.game

import java.time.LocalDateTime

data class PwGame(
    val createdAt: LocalDateTime,
    val gameSpeed: GameSpeed
)