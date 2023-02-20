package com.kozak.pw.data.star

import com.kozak.pw.data.PwMapper
import com.kozak.pw.domain.Size
import com.kozak.pw.domain.space.Star
import kotlinx.datetime.LocalDateTime

class StarMapper : PwMapper<StarEntity, Star> {

    override fun mapEntityToItem(entity: StarEntity): Star {
        return with(entity) {
            Star(mass, Size(sizeWidth, sizeHeight), starSystemId).apply {
                id = entity.id
                createdAt = LocalDateTime.parse(entity.createdAt)
                name = entity.name
                mass = entity.mass
                health = entity.health
            }
        }
    }

    override fun mapItemToEntity(item: Star): StarEntity {
        return with(item) {
            StarEntity(
                id = id,
                createdAt = item.createdAt.toString(),
                name = name,
                mass = mass,
                health = health,
                starSystemId = starSystemId,
                sizeWidth = size.width,
                sizeHeight = size.height
            )
        }
    }
}