package com.kozak.pw.data.planet

import com.kozak.pw.data.PwMapper
import com.kozak.pw.domain.space.Planet
import kotlinx.datetime.LocalDateTime

class PlanetMapper : PwMapper<PlanetEntity, Planet> {

    override fun mapEntityToItem(entity: PlanetEntity): Planet {
        return with(entity) {
            Planet(mass, getSize(this), starId).apply {
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
                starId = starId,
                sizeWidth = size!!.width,
                sizeHeight = size!!.height
            )
        }
    }
}