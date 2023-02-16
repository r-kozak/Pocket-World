package com.kozak.pw.domain.game

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


data class PwGame(
    val gameSpeed: GameSpeed,
    val createdAt: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    val id: Long = 1, // always 1 instance
)