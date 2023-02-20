package com.kozak.pw.data.star_system

import android.app.Application
import com.kozak.pw.data.AppDatabase
import com.kozak.pw.data.BaseRepositoryImpl
import com.kozak.pw.domain.space.StarSystem
import com.kozak.pw.domain.space.StarSystemRepository

class StarSystemRepositoryImpl(application: Application) :
    BaseRepositoryImpl<StarSystemEntity, StarSystem>(), StarSystemRepository {

    override val dao = AppDatabase.getInstance(application).starSystemDao()
    override val mapper = StarSystemMapper()
}