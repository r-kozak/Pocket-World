package com.kozak.pw.domain.person

import androidx.lifecycle.LiveData

interface PersonRepository {

    fun getAlivePersonsList(): LiveData<List<Person>>
}