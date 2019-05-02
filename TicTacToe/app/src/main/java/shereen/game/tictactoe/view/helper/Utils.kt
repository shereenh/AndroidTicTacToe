package shereen.game.tictactoe.view.helper

import shereen.game.tictactoe.model.TConstants

object Utils {

    fun getGameMode(mode: Int):String{
        var result = "NONE"
        when(mode){
            TConstants.SINGLE_MODE -> result = "Single Mode"
            TConstants.BLU_MODE -> "Bluetooth Mode"
            TConstants.WIFI_MODE -> "Wifi Mode"
            else -> "error"
        }
        return result
    }
}