package com.kozak.pw.domain.game

/**
 * @param   speedCoefficient   says us with what speed time passes. For example, with NORMAL (x1)
 * speed 126 real minutes = 1 PW year. If you want to know how much minutes in 1 PW year with another
 * speed, you must 126 divide to @speedCoefficient. With QUARTER speed 504 (126 / 0.25) real minutes
 * in 1 PW year
 */
enum class GameSpeed(val speedCoefficient: Double) {
    QUARTER(0.25),
    HALF(0.5),
    NORMAL(1.0),
    TEN(10.0),
    HUNDRED(100.0);

    companion object {
        private const val REAL_MINUTES_IN_1_PW_YEAR_NORMAL_SPEED = 126
    }

    fun realMinutesCountIn1PwYear(): Double =
        REAL_MINUTES_IN_1_PW_YEAR_NORMAL_SPEED / this.speedCoefficient

    /**
     * @return game speed in String representation with "x" prefix
     */
    fun representation() = "x$speedCoefficient"
}
