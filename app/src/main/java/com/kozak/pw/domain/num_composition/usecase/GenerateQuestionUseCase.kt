package com.kozak.pw.domain.num_composition.usecase

import com.kozak.pw.domain.num_composition.entity.Question
import com.kozak.pw.domain.num_composition.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {

    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}