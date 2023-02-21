package com.kozak.pw.data.star_system

import com.kozak.pw.data.PwMapper
import com.kozak.pw.domain.space.StarSystem
import kotlinx.datetime.LocalDateTime

class StarSystemMapper : PwMapper<StarSystemEntity, StarSystem> {

    override fun mapEntityToItem(entity: StarSystemEntity): StarSystem {
        return with(entity) {
            StarSystem(galaxyId).apply {
                id = entity.id
                createdAt = LocalDateTime.parse(entity.createdAt)
                name = entity.name
                mass = entity.mass
                health = entity.health
                size = getSizeNullable(entity)
            }
        }
    }

    override fun mapItemToEntity(item: StarSystem): StarSystemEntity {
        return with(item) {
            StarSystemEntity(
                id = id,
                createdAt = item.createdAt.toString(),
                name = name,
                mass = mass,
                health = health,
                galaxyId = galaxyId,
                sizeWidth = size?.width,
                sizeHeight = size?.height
            )
        }
    }
}