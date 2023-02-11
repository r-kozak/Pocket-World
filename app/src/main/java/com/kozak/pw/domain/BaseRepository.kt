package com.kozak.pw.domain

import androidx.lifecycle.LiveData

interface BaseRepository<T> {

    fun insert(item: T): Long

    fun insert(items: List<T>): List<Long>

    fun update(item: T)

    fun update(items: List<T>)

    fun delete(item: T)

    fun delete(items: List<T>)

    fun deleteAll()

    fun getItemSync(id: Long): T?

    fun getItemsSync(ids: List<Long>): List<T>?

    fun getItem(id: Long): LiveData<T>

    fun getItems(ids: List<Long>): LiveData<List<T>>
}