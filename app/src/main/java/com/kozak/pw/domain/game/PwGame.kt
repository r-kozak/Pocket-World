package com.kozak.pw.domain.game

import com.kozak.pw.PwConstants
import kotlinx.datetime.LocalDateTime


data class PwGame(
    val createdAt: LocalDateTime,
    val gameSpeed: GameSpeed,
    val id: Long = PwConstants.DEFAULT_ITEM_ID
)