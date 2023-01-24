package com.kozak.pw.domain.num_composition.entity

data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswer: Int,
    val minPercentOfRightAnswer: Int,
    val gameTimeSeconds: Int,
)