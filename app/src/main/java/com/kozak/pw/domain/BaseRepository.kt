package com.kozak.pw.domain

import androidx.lifecycle.LiveData

interface BaseRepository<T> {

    suspend fun insert(item: T): Long

    suspend fun insert(items: List<T>): List<Long>

    suspend fun update(item: T)

    suspend fun update(items: List<T>)

    suspend fun delete(item: T)

    suspend fun delete(items: List<T>)

    suspend fun deleteAll()

    suspend fun getItemSync(id: Long): T?

    suspend fun getItemsSync(ids: List<Long>): List<T>?

    suspend fun getItem(id: Long): LiveData<T>

    suspend fun getItems(ids: List<Long>): LiveData<List<T>>
}