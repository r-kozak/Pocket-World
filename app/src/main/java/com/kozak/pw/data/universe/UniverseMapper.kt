package com.kozak.pw.data.universe

import com.kozak.pw.data.PwMapper
import com.kozak.pw.domain.space.Universe
import kotlinx.datetime.LocalDateTime

class UniverseMapper : PwMapper<UniverseEntity, Universe> {

    override fun mapEntityToItem(entity: UniverseEntity): Universe {
        return Universe().apply {
            createdAt = LocalDateTime.parse(entity.createdAt)
            name = entity.name
            mass = entity.mass
            size = getSizeNullable(entity)
        }
    }

    override fun mapItemToEntity(item: Universe): UniverseEntity {
        return with(item) {
            UniverseEntity(
                id = id,
                createdAt = item.createdAt.toString(),
                name = name,
                mass = mass,
                health = health,
                sizeWidth = size?.width,
                sizeHeight = size?.height
            )
        }
    }
}