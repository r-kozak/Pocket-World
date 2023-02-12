package com.kozak.pw.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kozak.pw.domain.BaseRepository

abstract class BaseRepositoryImpl<E : BaseEntity, I> : BaseRepository<I> {

    abstract val dao: BaseDao<E>
    abstract val mapper: PwMapper<E, I>

    override suspend fun insert(item: I): Long {
        return dao.insert(mapper.mapItemToEntity(item))
    }

    override suspend fun update(item: I) {
        dao.update(mapper.mapItemToEntity(item))
    }

    override suspend fun delete(item: I) {
        dao.delete(mapper.mapItemToEntity(item))
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun getItemSync(id: Long): I? {
        return dao.getEntitySync(id)?.let {
            mapper.mapEntityToItem(it)
        }
    }

    override suspend fun getItemsSync(ids: List<Long>): List<I>? {
        val entities = dao.getEntitiesSync(ids)
        return entities?.let { mapper.mapEntitiesListToItemsList(it) }
    }

    override suspend fun getItem(id: Long): LiveData<I> {
        return Transformations.map(dao.getEntity(id)) {
            mapper.mapEntityToItem(it)
        }
    }

    override suspend fun getItems(ids: List<Long>): LiveData<List<I>> {
        return Transformations.map(dao.getEntities(ids)) {
            mapper.mapEntitiesListToItemsList(it)
        }
    }

    override suspend fun delete(items: List<I>) {
        val entities = mapper.mapItemsListToEntitiesList(items)
        dao.delete(entities)
    }

    override suspend fun update(items: List<I>) {
        val entities = mapper.mapItemsListToEntitiesList(items)
        dao.update(entities)
    }

    override suspend fun insert(items: List<I>): List<Long> {
        val entities = mapper.mapItemsListToEntitiesList(items)
        return dao.insert(entities)
    }
}