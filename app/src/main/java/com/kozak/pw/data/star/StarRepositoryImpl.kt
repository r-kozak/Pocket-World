package com.kozak.pw.data.star

import android.app.Application
import com.kozak.pw.data.AppDatabase
import com.kozak.pw.data.BaseRepositoryImpl
import com.kozak.pw.domain.space.Star
import com.kozak.pw.domain.space.StarRepository

class StarRepositoryImpl(application: Application) :
    BaseRepositoryImpl<StarEntity, Star>(), StarRepository {

    override val dao = AppDatabase.getInstance(application).starDao()
    override val mapper = StarMapper()
}