package com.kozak.pw.data.game

import android.app.Application
import com.kozak.pw.data.AppDatabase
import com.kozak.pw.domain.game.PwGame
import com.kozak.pw.domain.game.PwGameRepository

class PwGameRepositoryImpl(application: Application) : PwGameRepository {

    private val gameDao = AppDatabase.getInstance(application).gameDao()
    private val mapper = PwGameMapper()

    override suspend fun getGameInfo(): PwGame? {
        return gameDao.getGameInfo()?.let { mapper.mapEntityToItem(it) }
    }

    override suspend fun deleteGamesInfo() {
        gameDao.deleteGamesInfo()
    }

    override fun addNewPwGame(newPwGame: PwGame) {
        gameDao.insertGameInfo(mapper.mapItemToEntity(newPwGame))
    }
}