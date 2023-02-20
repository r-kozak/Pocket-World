package com.kozak.pw.data.star

import android.app.Application
import com.kozak.pw.data.AppDatabase
import com.kozak.pw.data.BaseRepositoryImpl
import com.kozak.pw.domain.space.Planet
import com.kozak.pw.domain.space.PlanetRepository

class PlanetRepositoryImpl(application: Application) :
    BaseRepositoryImpl<PlanetEntity, Planet>(), PlanetRepository {

    override val dao = AppDatabase.getInstance(application).planetDao()
    override val mapper = PlanetMapper()
}