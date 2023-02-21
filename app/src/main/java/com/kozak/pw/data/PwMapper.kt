package com.kozak.pw.data

import com.kozak.pw.domain.Size

interface PwMapper<E : BaseEntity, I> {

    fun mapEntityToItem(entity: E): I

    fun mapItemToEntity(item: I): E

    fun mapEntitiesListToItemsList(entities: List<E>): List<I> =
        entities.map { mapEntityToItem(it) }

    fun mapItemsListToEntitiesList(items: List<I>): List<E> =
        items.map { mapItemToEntity(it) }

    fun getSize(entity: PwAnyEntity): Size = Size(entity.sizeWidth!!, entity.sizeHeight!!)

    fun getSizeNullable(entity: PwAnyEntity): Size? =
        if (entity.sizeWidth != null && entity.sizeHeight != null)
            Size(entity.sizeWidth!!, entity.sizeHeight!!) else null
}