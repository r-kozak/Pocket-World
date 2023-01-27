package com.kozak.pw.domain.num_composition.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Level(val gameSettings: GameSettings) : Parcelable{

    TEST(GameSettings(10, 3, 50, 8)),
    EASY(GameSettings(10, 10, 70, 60)),
    NORMAL(GameSettings(20, 20, 80, 40)),
    HARD(GameSettings(30, 30, 90, 40));
}