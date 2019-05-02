package shereen.game.tictactoe.model

import shereen.game.tictactoe.R

object TConstants {

    const val LOGGER = "THREE-IN-A-ROW"

    const val SINGLE_MODE = 0
    const val BLU_MODE = 1
    const val WIFI_MODE = 2

    const val S_MODE = "Two Player Mode"
    const val C_MODE = "Computer Player Mode"
    const val B_MODE = "Bluetooth Mode"
//    const val W_MODE = "Wifi Mode"

    const val DATABASE_NAME = "tic.tac.toe"

    const val PLAYER_TABLE = "player"
    const val PLAYER_NAME = "pname"
    const val PLAYER_MODE = "mode"
    const val PLAYER_SCORE = "score"

    const val X = R.drawable.x_
    const val O = R.drawable.o_

    const val PREFS_FILE_NAME: String = "tictactoe_preferences"
    const val FIRST_TIME : String = "first_time"
    const val PLAYER_1: String = "player 1"
    const val PLAYER_2: String = "player 2"
    const val PLAYER_A: String = "player_a"
    const val PLAYER_B: String = "player_b"

    const val SCORE_A: String = "score_a"
    const val SCORE_B: String = "score_b"

    var prefList = listOf(
        FIRST_TIME,
        PLAYER_1,
        PLAYER_2,
        PLAYER_A,
        PLAYER_B,
        SCORE_A,
        SCORE_B
    )



}