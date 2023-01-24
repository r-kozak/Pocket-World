package com.kozak.pw.domain.num_composition.entity

enum class Level(val gameSettings: GameSettings) {

    TEST(GameSettings(10, 3, 50, 8)),
    EASY(GameSettings(10, 10, 70, 60)),
    NORMAL(GameSettings(20, 20, 80, 40)),
    HARD(GameSettings(30, 30, 90, 40));
}