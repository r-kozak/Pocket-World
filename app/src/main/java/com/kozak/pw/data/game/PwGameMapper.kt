package com.kozak.pw.data.game

import com.kozak.pw.domain.game.PwGame
import com.kozak.pw.domain.game.PwGameEntity
import kotlinx.datetime.LocalDateTime

class PwGameMapper {

    fun mapEntityToItem(pwGameEntity: PwGameEntity): PwGame {
        return with(pwGameEntity) {
            PwGame(
                id = id,
                createdAt = LocalDateTime.parse(createdAt),
                gameSpeed = gameSpeed
            )
        }
    }

    fun mapItemToEntity(pwGame: PwGame): PwGameEntity {
        return with(pwGame) {
            PwGameEntity(
                id = id,
                createdAt = LocalDateTime.toString(),
                gameSpeed = gameSpeed
            )
        }
    }
}