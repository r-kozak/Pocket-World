package com.kozak.pw.data.galaxy

import android.app.Application
import com.kozak.pw.data.AppDatabase
import com.kozak.pw.data.BaseRepositoryImpl
import com.kozak.pw.domain.space.Galaxy
import com.kozak.pw.domain.space.GalaxyRepository

class GalaxyRepositoryImpl(application: Application) :
    BaseRepositoryImpl<GalaxyEntity, Galaxy>(), GalaxyRepository {

    override val dao = AppDatabase.getInstance(application).galaxyDao()
    override val mapper = GalaxyMapper()
}