package com.kozak.pw.domain.game

class DestroyCurrentWorldUseCase(private val pwGameRepository: PwGameRepository) {

    suspend operator fun invoke() {
        pwGameRepository.destroyCurrentWorld()
    }
}