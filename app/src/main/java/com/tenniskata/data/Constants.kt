package com.tenniskata.data

enum class Player {
    PLAYER_1, PLAYER_2
}
enum class Points(val text: String, val points: Int, val position: Int) {
    LOVE("Love", 0, 0),
    FIFTEEN("Fifteen", 15, 1),
    THIRTY("Thirty", 30,2),
    FORTY("Forty", 40, 3)
}