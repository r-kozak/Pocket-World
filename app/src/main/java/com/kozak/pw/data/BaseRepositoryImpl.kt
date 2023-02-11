package com.kozak.pw.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kozak.pw.domain.BaseRepository

class BaseRepositoryImpl<E : BaseEntity, I>(val dao: BaseDao<E>, val mapper: BaseMapper<E, I>) :
    BaseRepository<I> {

    override fun insert(item: I): Long {
        return dao.insert(mapper.mapItemToEntity(item))
    }

    override fun update(item: I) {
        dao.update(mapper.mapItemToEntity(item))
    }

    override fun delete(item: I) {
        dao.delete(mapper.mapItemToEntity(item))
    }

    override fun deleteAll() {
        dao.deleteAll()
    }

    override fun getItemSync(id: Long): I? {
        return dao.getEntitySync(id)?.let {
            mapper.mapEntityToItem(it)
        }
    }

    override fun getItemsSync(ids: List<Long>): List<I>? {
        val entities = dao.getEntitiesSync(ids)
        return entities?.let { mapper.mapEntitiesListToItemsList(it) }
    }

    override fun getItem(id: Long): LiveData<I> {
        return Transformations.map(dao.getEntity(id)) {
            mapper.mapEntityToItem(it)
        }
    }

    override fun getItems(ids: List<Long>): LiveData<List<I>> {
        return Transformations.map(dao.getEntities(ids)) {
            mapper.mapEntitiesListToItemsList(it)
        }
    }

    override fun delete(items: List<I>) {
        val entities = mapper.mapItemsListToEntitiesList(items)
        dao.delete(entities)
    }

    override fun update(items: List<I>) {
        val entities = mapper.mapItemsListToEntitiesList(items)
        dao.update(entities)
    }

    override fun insert(items: List<I>): List<Long> {
        val entities = mapper.mapItemsListToEntitiesList(items)
        return dao.insert(entities)
    }
}