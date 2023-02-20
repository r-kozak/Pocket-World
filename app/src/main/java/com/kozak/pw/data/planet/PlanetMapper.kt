package com.kozak.pw.data.star

import com.kozak.pw.data.PwMapper
import com.kozak.pw.domain.Size
import com.kozak.pw.domain.space.Planet
import kotlinx.datetime.LocalDateTime

class PlanetMapper : PwMapper<PlanetEntity, Planet> {

    override fun mapEntityToItem(entity: PlanetEntity): Planet {
        return with(entity) {
            Planet(mass, Size(sizeWidth, sizeHeight), starSystemId).apply {
                id = entity.id
                createdAt = LocalDateTime.parse(entity.createdAt)
                name = entity.name
                mass = entity.mass
                health = entity.health
            }
        }
    }

    override fun mapItemToEntity(item: Planet): PlanetEntity {
        return with(item) {
            PlanetEntity(
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