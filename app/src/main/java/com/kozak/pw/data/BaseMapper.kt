package com.kozak.pw.data

interface BaseMapper<E: BaseEntity, I> {

    fun mapEntityToItem(entity: E): I

    fun mapItemToEntity(item: I): E

    fun mapEntitiesListToItemsList(entities: List<E>): List<I>

    fun mapItemsListToEntitiesList(entities: List<I>): List<E>
}