package com.kozak.pw.domain.game

class IsGameStartedUseCase(private val pwGameRepository: PwGameRepository) {

    suspend operator fun invoke(): Boolean {
        return pwGameRepository.getGameInfo() != null
    }
}