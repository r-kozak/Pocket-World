package com.kozak.pw.data

abstract class BaseMapper<E: BaseEntity, I> {

    abstract fun mapEntityToItem(entity: E): I

    abstract fun mapItemToEntity(item: I): E

    abstract fun mapEntitiesListToItemsList(entities: List<E>): List<I>

    abstract fun mapItemsListToEntitiesList(entities: List<I>): List<E>
}