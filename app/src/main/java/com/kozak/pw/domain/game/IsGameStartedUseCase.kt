package com.kozak.pw.domain.game

class IsGameStartedUseCase(private val pwGameRepository: PwGameRepository) {

    operator fun invoke(): Boolean = pwGameRepository.getGameInfo() != null
}