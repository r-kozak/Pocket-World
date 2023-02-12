package com.kozak.pw.data.game

import android.app.Application
import com.kozak.pw.data.AppDatabase
import com.kozak.pw.data.BaseRepositoryImpl
import com.kozak.pw.domain.game.PwGame
import com.kozak.pw.domain.game.PwGameEntity
import com.kozak.pw.domain.game.PwGameRepository

class PwGameRepositoryImpl(application: Application) :
    BaseRepositoryImpl<PwGameEntity, PwGame>(), PwGameRepository {

    override val dao = AppDatabase.getInstance(application).gameDao()
    override val mapper = PwGameMapper()

    override suspend fun getGameInfo(): PwGame? {
        return dao.getGameInfo()?.let { mapper.mapEntityToItem(it) }
    }
}