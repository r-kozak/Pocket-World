package com.kozak.pw.domain.num_composition.usecase

import com.kozak.pw.domain.num_composition.entity.GameSettings
import com.kozak.pw.domain.num_composition.entity.Level
import com.kozak.pw.domain.num_composition.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}