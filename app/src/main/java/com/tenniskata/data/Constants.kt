package com.tenniskata.data

enum class Player {
    PLAYER_1, PLAYER_2
}
enum class Points(val text: String, val points: Int) {
    LOVE("Love", 0),
    FIFTEEN("Fifteen", 15),
    THIRTY("Thirty", 30),
    FORTY("Forty", 40)
}