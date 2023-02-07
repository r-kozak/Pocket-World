package com.kozak.pw.domain

data class Coordinate(val x: Int, val y: Int) {

    val isInBounds = x >= 0 && y >= 0

    operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)
}
