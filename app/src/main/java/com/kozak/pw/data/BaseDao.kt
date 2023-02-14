package com.kozak.pw.data

import android.annotation.SuppressLint
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

/**
 * Base Dao for all Pw Dao-s
 * @link https://medium.com/@berryhuang/android-room-generic-dao-27cfc21a4912
 */
abstract class BaseDao<T : BaseEntity>(
    private val tableName: String,
    private val roomDatabase: RoomDatabase
) {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(entities: List<T>): List<Long>

    @Update
    abstract suspend fun update(entity: T)

    @Update
    abstract suspend fun update(entities: List<T>)

    @Delete
    abstract suspend fun delete(entity: T)

    @Delete
    abstract suspend fun delete(entities: List<T>)

    @RawQuery
    protected abstract suspend fun deleteAll(query: SupportSQLiteQuery): Long

    suspend fun deleteAll() {
        val query = SimpleSQLiteQuery("DELETE FROM $tableName")
        deleteAll(query)
    }

    @RawQuery
    protected abstract suspend fun getEntitiesSync(query: SupportSQLiteQuery): List<T>?

    suspend fun getEntitySync(id: Long): T? {
        return getEntitiesSync(listOf(id))?.firstOrNull()
    }

    suspend fun getEntitiesSync(ids: List<Long>): List<T>? {
        val query = buildQueryWithIds(ids)
        return getEntitiesSync(query)
    }

    private fun buildQueryWithIds(ids: List<Long>): SimpleSQLiteQuery {
        val result = StringBuilder()
        for (index in ids.indices) {
            if (index != 0) {
                result.append(",")
            }
            result.append("'").append(ids[index]).append("'")
        }
        return SimpleSQLiteQuery("SELECT * FROM $tableName WHERE id IN ($result);")
    }

    fun getEntity(id: Long): LiveData<T> {
        val resultLiveData = MediatorLiveData<T>()
        resultLiveData.addSource(getEntities(listOf(id))) { obj ->
            resultLiveData.postValue(obj?.firstOrNull())
        }
        return resultLiveData
    }

    @RawQuery
    protected abstract fun getEntities(query: SupportSQLiteQuery): List<T>?

    @SuppressLint("RestrictedApi")
    fun getEntities(ids: List<Long>): LiveData<List<T>> {
        return object : ComputableLiveData<List<T>>() {
            private var observer: InvalidationTracker.Observer? = null

            override fun compute(): List<T>? {
                if (observer == null) {
                    observer = object : InvalidationTracker.Observer(tableName) {

                        override fun onInvalidated(tables: Set<String>) = invalidate()
                    }
                    roomDatabase.invalidationTracker.addWeakObserver(observer as InvalidationTracker.Observer)
                }
                val query = buildQueryWithIds(ids)
                return getEntities(query)
            }
        }.liveData
    }
}