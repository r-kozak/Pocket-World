package com.kozak.pw.data.universe

import android.app.Application
import com.kozak.pw.data.AppDatabase
import com.kozak.pw.data.BaseRepositoryImpl
import com.kozak.pw.domain.space.Universe
import com.kozak.pw.domain.space.UniverseRepository

class UniverseRepositoryImpl(application: Application) :
    BaseRepositoryImpl<UniverseEntity, Universe>(), UniverseRepository {

    override val dao = AppDatabase.getInstance(application).universeDao()
    override val mapper = UniverseMapper()
}