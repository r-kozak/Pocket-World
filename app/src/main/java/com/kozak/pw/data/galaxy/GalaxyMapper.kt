package com.kozak.pw.data.galaxy

import com.kozak.pw.data.PwMapper
import com.kozak.pw.domain.space.Galaxy
import kotlinx.datetime.LocalDateTime

class GalaxyMapper : PwMapper<GalaxyEntity, Galaxy> {

    override fun mapEntityToItem(entity: GalaxyEntity): Galaxy {
        return with(entity) {
            Galaxy(universeId).apply {
                id = entity.id
                createdAt = LocalDateTime.parse(entity.createdAt)
                name = entity.name
                mass = entity.mass
                health = entity.health
            }
        }
    }

    override fun mapItemToEntity(item: Galaxy): GalaxyEntity {
        return with(item) {
            GalaxyEntity(
                id = id,
                createdAt = item.createdAt.toString(),
                name = name,
                mass = mass,
                health = health,
                universeId = universeId
            )
        }
    }
}