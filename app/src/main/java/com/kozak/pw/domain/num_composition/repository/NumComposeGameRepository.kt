package com.kozak.pw.domain.num_composition.repository

import com.kozak.pw.domain.num_composition.entity.GameSettings
import com.kozak.pw.domain.num_composition.entity.Level
import com.kozak.pw.domain.num_composition.entity.Question

interface NumComposeGameRepository {

    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings
}