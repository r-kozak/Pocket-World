package com.kozak.pw.domain.person

import androidx.lifecycle.LiveData
import com.kozak.pw.domain.BaseRepository

interface PersonRepository : BaseRepository<Person> {

    fun getAlivePersonsList(): LiveData<List<Person>>
}