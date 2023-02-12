package com.kozak.pw.data

interface PwMapper<E : BaseEntity, I> {

    fun mapEntityToItem(entity: E): I

    fun mapItemToEntity(item: I): E

    fun mapEntitiesListToItemsList(entities: List<E>): List<I> =
        entities.map { mapEntityToItem(it) }

    fun mapItemsListToEntitiesList(items: List<I>): List<E> =
        items.map { mapItemToEntity(it) }
}