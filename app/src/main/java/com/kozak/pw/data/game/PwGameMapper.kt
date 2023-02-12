package com.kozak.pw.data.game

import com.kozak.pw.data.PwMapper
import com.kozak.pw.domain.game.PwGame
import com.kozak.pw.domain.game.PwGameEntity
import kotlinx.datetime.LocalDateTime

class PwGameMapper : PwMapper<PwGameEntity, PwGame> {

    override fun mapEntityToItem(entity: PwGameEntity): PwGame {
        return with(entity) {
            PwGame(
                id = id,
                createdAt = LocalDateTime.parse(createdAt),
                gameSpeed = gameSpeed
            )
        }
    }

    override fun mapItemToEntity(item: PwGame): PwGameEntity {
        return with(item) {
            PwGameEntity(
                id = id,
                createdAt = item.createdAt.toString(),
                gameSpeed = gameSpeed
            )
        }
    }
}